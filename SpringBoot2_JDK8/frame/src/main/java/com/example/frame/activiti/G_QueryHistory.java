package com.example.frame.activiti;

import com.example.frame.domain.Constants;
import org.activiti.engine.HistoryService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.repository.ProcessDefinition;

import java.util.List;

/**
 * @Description 👀✔🐱‍🐉❌
 * @Author RainbowJier
 * @Date 2024/6/27
 */
public class G_QueryHistory {
    private static final String DEPLOYMENT_ID = Constants.DEPLOYMENT_ID;
    private static final ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();

    public static void main(String[] args) {
        // 获取流程定义
        ProcessDefinition processDefinition = getProcessDefinition();

        // 获得HistoryService对象
        HistoryService historyService = engine.getHistoryService();

        // 查询历史信息
        assert processDefinition != null;
        List<HistoricActivityInstance> results = historyService
                        .createHistoricActivityInstanceQuery()
                        .processDefinitionId(processDefinition.getId())  // 根据流程定义id
                        .orderByHistoricActivityInstanceStartTime().desc()  //根据节点的开始时间逆序的排序
                        .list();

        System.out.println(results);

        for (HistoricActivityInstance result : results) {
            System.out.println(result.getActivityId());
            System.out.println(result.getActivityName());
            System.out.println(result.getActivityType());
            System.out.println(result.getAssignee());
            System.out.println("------------");
        }
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
