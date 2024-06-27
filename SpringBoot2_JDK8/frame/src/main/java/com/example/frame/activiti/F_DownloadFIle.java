package com.example.frame.activiti;

import com.example.frame.domain.Constants;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.ProcessDefinition;

import java.io.*;

/**
 * @Description 👀✔🐱‍🐉❌下载bpmn和png文件。
 * @Author RainbowJier
 * @Date 2024/6/27
 */
public class F_DownloadFIle {
    private static final String DEPLOYMENT_ID = Constants.DEPLOYMENT_ID;
    private static final  ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();

    public static void main(String[] args) throws IOException {
        RepositoryService repositoryService = engine.getRepositoryService();

        // 流程定义对象
        ProcessDefinition processDefinition = getProcessDefinition();

        // 获得png图⽚的输⼊流
        try (InputStream pngInputStream = repositoryService.getResourceAsStream(
                processDefinition.getDeploymentId(),
                processDefinition.getDiagramResourceName());
             FileOutputStream pngFos = new FileOutputStream("leave1.png")) {

            // 读写PNG数据
            byte[] buffer = new byte[1024];
            int len;
            while ((len = pngInputStream.read(buffer)) != -1) {
                pngFos.write(buffer, 0, len);
            }
        }

        // 获得bpmn⽂件的输⼊流
        try (InputStream bpmnInputStream = repositoryService.getResourceAsStream(
                processDefinition.getDeploymentId(),
                processDefinition.getResourceName());
             InputStreamReader bpmnReader = new InputStreamReader(bpmnInputStream, "UTF-8");
             BufferedReader bufferedReader = new BufferedReader(bpmnReader);
             FileOutputStream bpmnFos = new FileOutputStream("leave1.bpmn.xml");
             OutputStreamWriter bpmnWriter = new OutputStreamWriter(bpmnFos, "UTF-8")) {

            // 读写BPMN数据
            char[] buffer = new char[1024];
            int len;
            while ((len = bufferedReader.read(buffer)) != -1) {
                bpmnWriter.write(buffer, 0, len);
            }
        }

        System.out.println("下载完成了");
    }

    /**
     * 根据部署id获取流程定义实例
     */
    public static ProcessDefinition getProcessDefinition() {
        RepositoryService repositoryService = engine.getRepositoryService();

        // 根据部署ID获取流程定义
        ProcessDefinition processDefinition = repositoryService
                .createProcessDefinitionQuery()      // 创建流程定义查询对象
                .deploymentId(DEPLOYMENT_ID)         // 指定部署ID
                .singleResult();
        // 获取唯一结果
        if (processDefinition == null) {
            System.out.println("未找到指定部署ID的流程定义");
            return null;
        }
        return processDefinition;
    }
}
