package com.example.frame.activiti;

import com.example.frame.domain.Constants;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;

/**
 * @Description 👀✔🐱‍🐉❌删除流程实例
 * @Author RainbowJier
 * @Date 2024/6/27
 */
public class I_DeleteProcessDefinition {
    private static final String DEPLOYMENT_ID = Constants.DEPLOYMENT_ID;
    private static final ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();

    public static void main(String[] args) {
        RepositoryService repositoryService = engine.getRepositoryService();

        // 删除部署的流程,cascade:true，删除正在进⾏的流程实例，否则⽆法删除
        repositoryService.deleteDeployment(DEPLOYMENT_ID,true);

        System.out.println("流程定义删除成功");
    }
}
