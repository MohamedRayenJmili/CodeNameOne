/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.storeship.Evenement;

import com.codename1.charts.ChartComponent;
import com.codename1.charts.models.CategorySeries;
import com.codename1.charts.renderers.DefaultRenderer;  
import com.codename1.charts.renderers.SimpleSeriesRenderer;
import com.codename1.charts.util.ColorUtil;
import com.codename1.charts.views.PieChart;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.storeship.entities.Evenement_entite;
import com.storeship.services.ServiceEvenement;

import java.util.HashMap;
import java.util.List;
import java.util.Map;



/**
 *
 * @author oumayma
 */
public class statForm extends Form {

    public statForm(Form previous) {
        setTitle("Nombre max de participants par événement");


        // Set up the chart values and colors
        List<Evenement_entite> offres = ServiceEvenement.getInstance().affichageEvenement();
        Map<String, Integer> countByCategory = new HashMap<>();
for (Evenement_entite offre : offres) {
    int category = offre.getNbMax_place();
    if (countByCategory.containsKey(offre.getTitreEvenement())) {
        countByCategory.put(offre.getTitreEvenement(), countByCategory.get(offre.getTitreEvenement()) + category);
    } else {
        countByCategory.put(offre.getTitreEvenement(), category);
    }
}

        // Convertir les données en tableau pour la construction du graphique
        String[] categories = countByCategory.keySet().toArray(new String[countByCategory.size()]);
        double[] values = new double[categories.length];
        for (int i = 0; i < categories.length; i++) {
            values[i] = countByCategory.get(categories[i]);
        }

        // Set up the chart renderer
        int[] colors = new int[countByCategory.size()];
        int i = 0;
        for (Map.Entry<String, Integer> entry : countByCategory.entrySet()) {
            categories[i] = entry.getKey();
            values[i] = entry.getValue();
            colors[i] = ColorUtil.BLUE + i * 100;
            i++;
        }

        DefaultRenderer renderer = buildCategoryRenderer(colors);
        renderer.setZoomButtonsVisible(true);
        renderer.setZoomEnabled(true);
        renderer.setChartTitleTextSize(20);
        renderer.setLabelsTextSize(100);
        renderer.setLabelsColor(ColorUtil.BLACK);
        renderer.setLegendTextSize(100);
        renderer.setDisplayValues(true);
        renderer.setShowLabels(false);
        SimpleSeriesRenderer r = renderer.getSeriesRendererAt(0);
        r.setGradientEnabled(true);
        r.setGradientStart(0, ColorUtil.BLUE);
        r.setGradientStop(0, ColorUtil.GREEN);
        r.setHighlighted(true);

        // Create the chart and chart component
        PieChart chart = new PieChart(buildCategoryDataset(categories, values), renderer);
        ChartComponent chartComponent = new ChartComponent(chart);
        add(chartComponent);

        // Set up the back button
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, (evt) -> {
            previous.showBack();
        });
    }

    /**
     * Creates a renderer for the specified colors.
     */
    private DefaultRenderer buildCategoryRenderer(int[] colors) {
        DefaultRenderer renderer = new DefaultRenderer();
        renderer.setLabelsTextSize(15);
        renderer.setLegendTextSize(15);
        renderer.setMargins(new int[]{20, 30, 15, 0});
        for (int color : colors) {
            SimpleSeriesRenderer r = new SimpleSeriesRenderer();
            r.setColor(color);
            renderer.addSeriesRenderer(r);
        }
        return renderer;
    }

    /**
     * Builds a category dataset using the provided categories and values.
     */
  protected CategorySeries buildCategoryDataset(String[] categories, double[] values) {
    CategorySeries series = new CategorySeries("Nombre max de participants par événement");
    for (int i = 0; i < categories.length; i++) {
        series.add(categories[i] + " (" + (int) values[i] + ")", values[i]);
    }
    return series;
}

}
