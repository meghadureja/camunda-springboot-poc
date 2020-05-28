package com.bnym.workflow.adapter;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.ExecutionListener;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class MapDmnResult implements ExecutionListener {
    @Override
    public void notify(DelegateExecution delegateExecution) throws Exception {

        Set<String> riskLevels = new HashSet<String>();
        Object oDMNresult = delegateExecution.getVariable("riskDMNresult");
        System.out.println("****** oDMNresult ***** " + oDMNresult.toString());
        riskLevels.add(oDMNresult.toString());
        String accumulatedRiskLevel = "green";
        if (riskLevels.contains("red")) {
            accumulatedRiskLevel = "red";
        } else if (riskLevels.contains("yellow")) {
            accumulatedRiskLevel = "yellow";
        }
        delegateExecution.setVariable("riskLevel", accumulatedRiskLevel);
    }
}
