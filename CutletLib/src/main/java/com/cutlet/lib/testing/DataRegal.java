/*
 * Copyright (C) 2017 rudolf.muehlbauer@gmail.com
 */
package com.cutlet.lib.testing;

import com.cutlet.lib.model.Dimension;
import com.cutlet.lib.model.Panel;
import com.cutlet.lib.model.PanelInstance;
import com.cutlet.lib.model.Project;
import com.cutlet.lib.model.StockSheet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rmuehlba
 */
public class DataRegal extends AbstractTestData {

    @Override
    public Project getData() {

        final StockSheet sheet = makeSheet(2500, 1250);
        int i;

        final double w = 600;
        final boolean canRotate = false;
        List<Panel> inputs = new ArrayList<>();
        inputs.add(new Panel(sheet, new Dimension(500, w), "side short", canRotate, 8));
        inputs.add(new Panel(sheet, new Dimension(560, w), "side long", canRotate, 4));
        inputs.add(new Panel(sheet, new Dimension(450, w), "top_bottom", canRotate, 12));
        inputs.add(new Panel(sheet, new Dimension(25, w), "halter", canRotate, 16));

        return makeProject(inputs, 4);
    }

    @Override
    public String getTitle() {
        return "DataRegal";
    }

}
