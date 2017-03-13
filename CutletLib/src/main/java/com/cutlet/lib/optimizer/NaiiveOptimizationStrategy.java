/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cutlet.lib.optimizer;

import com.cutlet.lib.model.Panel;
import com.cutlet.lib.model.Project;
import com.cutlet.lib.data.cuttree.FreeNode;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 *
 * @author rmuehlba
 */
public class NaiiveOptimizationStrategy extends AbstractOptimizationStrategy {

    private final Logger log = Logger.getLogger("NaiiveOptimzer");

    @Override
    public OptimizationResult optimize(Project project, FitnessFunction fitness) {

        OptimizationResult optimizationResult = new OptimizationResult();

        List<Panel> panels = project.getPanels().stream()
                .sorted((b, a) -> Double.compare(a.getDimension().getLength(), b.getDimension().getLength()))
                .collect(Collectors.toList());

//        optimizationResult.createNewLayout(p);

        for (Panel p : panels) {
            FreeNode candidate = findSheet(optimizationResult, p);
            if (candidate == null) {
                log.info("failed to find free space on sheet, creating a new layout");
                
                optimizationResult.createNewLayout(p);
                candidate = findSheet(optimizationResult, p);
            }

            candidate = cutToFit(candidate, project, p);
            candidate.setPanel(p);
        }

        return optimizationResult;
    }
}