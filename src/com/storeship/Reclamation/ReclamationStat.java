/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.storeship.Reclamation;

import com.codename1.charts.ChartComponent;
import com.codename1.charts.models.CategorySeries;
import com.codename1.charts.models.SeriesSelection;
import com.codename1.charts.renderers.DefaultRenderer;
import com.codename1.charts.renderers.SimpleSeriesRenderer;
import com.codename1.charts.util.ColorUtil;
import com.codename1.charts.views.PieChart;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Font;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.storeship.entities.Reclamation;
import com.storeship.gui.SessionManager;
import com.storeship.services.ServiceReclamation;
import java.util.ArrayList;

public class ReclamationStat extends Form {
    
    public ReclamationStat() {
        // Get the reclamations with id 8 from the service
        super("Reclamation Stats", BoxLayout.y());
        Toolbar tb = new Toolbar(false);
        setToolbar(tb);
                tb.setUIID("Toolbar");
        getTitleArea().setUIID("Toolbar");
        Form previous = Display.getInstance().getCurrent();
        tb.setBackCommand("", e -> previous.showBack());
        setTitle("STats");
        ArrayList<Reclamation> reclamations = ServiceReclamation.getInstance().getReclamationsById(SessionManager.getUser().getId());
        
        // Count the number of reclamations with each etat
        int acceptedCount = 0;
        int refusedCount = 0;
        int pendingCount = 0;
        for (Reclamation r : reclamations) {
            String etat = r.getEtat();
            if (etat.equals("accepted")) {
                acceptedCount++;
            } else if (etat.equals("refused")) {
                refusedCount++;
            } else if (etat.equals("pending")) {
                pendingCount++;
            }
        }
        
        // Create the chart data
        CategorySeries series = new CategorySeries("Reclamation Etat");
        series.add("Accepted", acceptedCount);
        series.add("Refused", refusedCount);
        series.add("Pending", pendingCount);
        
       // Set the chart colors and labels
        int[] colors = new int[] {0x00FF00, 0xFF0000, 0xFFFF00};
        String[] labels = new String[] {"accepted", "refused", "pending"};
        DefaultRenderer renderer = new DefaultRenderer();
        for (int i = 0; i < colors.length; i++) {
            SimpleSeriesRenderer r = new SimpleSeriesRenderer();
            r.setColor(colors[i]);
            renderer.addSeriesRenderer(r);
            
            // Add the label with the etat name and color
            Label label = new Label(labels[i]);
            label.getUnselectedStyle().setFgColor(colors[i]);
            label.getUnselectedStyle().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_SMALL));
             Container container = new Container(new BorderLayout());

            container.add(BorderLayout.EAST, label);
        }
        
        // Create the chart component
        ChartComponent chart = new ChartComponent(new PieChart(series, renderer));
        chart.getStyle().setMarginTop(20);
        
        // Add the chart component to a container
        Container container = new Container(new BorderLayout());
        container.add(BorderLayout.CENTER, chart);
        
        // Add the container to the form
        add(container);
        
        // Set the title of the form
        setTitle("Reclamation Chart");
    }
    
}
