import java.awt.Dimension;
import javax.swing.JFrame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYDataset;

public class GraficoXY {
    private final String tituloGrafico;
    private final String descEixoX;
    private final String descEixoY;
    private final XYDataset datasetAreaXY;
    private JFreeChart grafico;

    public GraficoXY(String tituloGrafico, String descEixoX, String descEixoY, XYDataset datasetAreaXY) {
        this.tituloGrafico = tituloGrafico;
        this.descEixoX = descEixoX;
        this.descEixoY = descEixoY;
        this.datasetAreaXY = datasetAreaXY;
        this.criarGrafico();
    }
    
    private void criarGrafico(){
        this.grafico = ChartFactory.createScatterPlot(
                tituloGrafico,
                descEixoX,   //descrição do eixo x
                descEixoY, //descrição do eixo y
                this.datasetAreaXY,
                PlotOrientation.VERTICAL,
                true,   //mostra a legenda
                false,  //dicas
                false   //gerar url
        );
    }
    
    public void exibirGraficoEmFrame(int largura, int altura) {
        JFrame frame = new JFrame(this.tituloGrafico);
        frame.setPreferredSize(new Dimension(largura, altura));
        ChartPanel painelDoGrafico = new ChartPanel(this.grafico);
        painelDoGrafico.setPreferredSize(new Dimension(largura, altura));
        frame.add(painelDoGrafico);
        frame.pack();
        frame.setVisible(true);
    }
}
