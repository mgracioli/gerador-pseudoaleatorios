import java.lang.Math;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class Main {
    public static double fatorial(double valor){
        if(valor == 1 || valor == 0){
            return 1;
        }else{
            return valor*(fatorial(valor-1));
        }
    }

    public static void geraNumeros(int semente){ 
        /******** GERADORES COM ESTRUTURAS INTERNAS (NÃO USAR) **********/ 
        //GERADOR 1
        // final int a = 45;  
        // final int c = 1;
        // final int M = 1024;
        
        //GERADOR 2
        // final int a = 45;  
        // final int c = 0;
        // final int M = 1024;

        //GERADOR 3
        // final int a = 383;  
        // final int c = 263;
        // final int M = 1000;

        //GERADOR 4
        // final int a = 97;  
        // final int c = 0;
        // final int M = 131072;


        /*********** GERADOR SEM ESTRUTURAS INTERNAS ************/
        //GERADOR 5 - MEU GERADOR CONGRUENCIAL LINEAR
        final int a = 16807;  
        final int c = 0;
        final int M = Integer.MAX_VALUE;

        float[] x = new float[semente]; /*armazena os valores pseudoaleatórios gerados*/
        long w = semente; /*w recebe o valor da semente ou valor inicial X(0)*/
        int i = 0;

        for(i=0; i<semente; i++){ 
            x[i] = (float) w/M;  /*w/M para gerar o valor entre 0 e 1*/
            w = ((a * w) + c) % M;
        } 


        /************* MÉDIA ******************/
        // float media = 0;
        // int n = semente-2; /*-2 porque eu descartei os dois primeiros pseudoaleatórios*/

        // for(i=2; i<semente; i++){ /*i = 2 porque precisa descartar os dois primeiros pseudoaleatórios gerados*/
        //     media += x[i];
        // }
        
        // media = media/n;
        // System.out.printf("Média: %f%n", media);


        /************* DESVIO PADRÃO ******************/
        // double desvPad = 0;
        // double S_XiMedQuad = 0;

        // for(i=2; i<semente; i++){
        //     S_XiMedQuad += Math.pow( (x[i]-media), 2 );
        // }

        // desvPad = Math.sqrt( (S_XiMedQuad/(n-1)) );
        // System.out.printf("Desvio Padrão: %f\n", desvPad);


        /************* COVARIÂNCIA ******************/
        // double cov = 0;
        // double S_xy = 0;
        // double S_x = 0;
        // double S_y = 0;

        // n = n/2;    /* n/2 porque o n, para a covariância, é o número de pares ordenados (x,y) e, não, a quantidade total de valores pseudo aleatórios*/

        // /*semente-1 para não dar erro ao somar o último x[n+1]*/
        // for(i=2; i<semente; i=i+2){
        //     S_xy += x[i]*x[i+1];     
        //     S_x += x[i]; /*S_x contem os valores de índice par*/
        //     S_y += x[i+1];   /*S_y contem os valores de índice ímpar*/
        // }

        // cov = ( (n*S_xy) - (S_x*S_y) )/( n*(n-1) );
        // System.out.printf("Covariância: %f\n", cov);


        /************* QUI QUADRADO ******************/
        // float quiQuad = 0;
        // int[] intervalos = new int[10]; /*10 porque os dados são separados em 10 intervalos*/

        // n = semente-2;  /*atualiza o valor de n que foi modificado no cálculo da covariância*/

        // for(i=2; i<semente; i++){
        //     int pos = (int) Math.floor( x[i]*10 );                
        //     intervalos[pos]++;
        // }

        // for(i=0; i<intervalos.length; i++){    /*Se X² calculado < X² tabelado: Aceita-se Ho - Hipótese nula (H0): frequências observadas = frequências esperadas, ou seja, não há diferenças significativas entre os valores obtidos e os teóricos*/
        //     quiQuad += Math.pow( (intervalos[i]-(n/10)), 2 ) / ( n/10 ); 
        // }

        // System.out.printf("Qui Quadrado: %f\n", quiQuad);


        /*************** GRÁFICO BIDIMENSIONAL ***************/
        /** deixei comentado para não fica gerando o gráfico a todo momento e não conflitar com os outros exercícios que também geram gráficos **/
        // XYSeries pontosDoGrafico = new XYSeries("Valores pseudo aleatórios");
        
        // for(i=2; i<semente; i=i+2){
        //     pontosDoGrafico.add(x[i], x[i+1]);
        // }
        
        // XYDataset datasetXY = new XYSeriesCollection(pontosDoGrafico);
        // GraficoXY graficoPontosXY = new GraficoXY("Gráfico pseudo-aleatórios", "valores com índice par (x)", "valores com índice ímpar (y)", datasetXY);
        // graficoPontosXY.exibirGraficoEmFrame(800, 640);


        /****** ESTIMATIVA DO VALOR DE PI PELO MÉTODO DE MONTE CARLO ********/
        // double d = 0;
        // double xPar = 0;
        // double xImpar = 0;
        // double pi = 0;
        // int pInternos = 0;
        // int pQuadrado = 0;

        // for(i=2; i<semente; i=i+2){
        //     xPar = x[i];
        //     xImpar = x[i+1];       
        //     d = (Math.pow(xPar, 2) + Math.pow(xImpar, 2));

        //     if(d<=1){
        //         pInternos++;
        //     }
        //     pQuadrado++; 
        // }

        // pi = (double) 4 * pInternos/pQuadrado;
        // System.out.printf("\nPI: %f\n", pi);
        

        /******* CÁLCULO DO VALOR APROXIMADO DE INTEGRAIS PELO MÉTODO DE MONTE CARLO *********/
        // int somaA = 0;
        // int somaB = 0;
        // int num = (semente-2)/2;    /*quantidade total de pontos gerados*/
        // double fxA = 0;   /*armazena o resultado da integral a)*/
        // double fxB = 0;   /*armazena o resultado da integral b)*/
        // double _x = 0;
        // double _y = 0;
        // double integralA = 0;
        // double integralB = 0;
        
        // for(i=2; i<semente; i=i+2){
        //     _x = x[i];
        //     _y = x[i+1];

        //     fxA = Math.sin(_x)*Math.cosh(_x);
        //     fxB = Math.sin(_x)*Math.sinh(_x);

        //     if(_y < fxA){
        //         somaA++;
        //     }
        //     if(_y < fxB){
        //         somaB++;
        //     }
        // }     

        // integralA = (double) somaA/num;
        // integralB = (double) somaB/num;
        // System.out.printf("\nf(x) = sen(x) cosh(x) (R.: %f)\n", integralA);
        // System.out.printf("f(x) = sen(x) senh(x) (R.: %f)\n\n", integralB);
    
    
        /******** GERAÇÃO DE VARIÁVEIS ALEATÓRIAS COM DISTRIBUIÇÃO UNIFORME *********/
        // XYSeries pontosDoGrafico = new XYSeries("Valores pseudo aleatórios");
        // double xi = 0;
        // double fx = 0;
        // double A = 5;   /*esse valor de A pode variar*/
        // double B = 15;   /*esse valor de B pode variar*/

        // for(i=2; i<semente; i++){
        //     xi = (B-A)*x[i]+A;
        //     fx = x[i];
        //     pontosDoGrafico.add(xi, fx);
        // }
        
        // XYDataset datasetXY = new XYSeriesCollection(pontosDoGrafico);
        // GraficoXY graficoPontosXY = new GraficoXY("Variáveis aleatórias com dstribuição Uniforme", "x","f(x)", datasetXY);
        // graficoPontosXY.exibirGraficoEmFrame(800, 640);


        /******** GERAÇÃO DE VARIÁVEIS ALEATÓRIAS COM DISTRIBUIÇÃO EXPONENCIAL *********/
        // XYSeries pontosDoGrafico = new XYSeries("Variáveis aleatórias");
        // double xi = 0;  //xi tem que ser >= 0
        // double fx = 0;
        // double alfa = 5;   //alfa tem que ser > 0

        // for(i=2; i<semente; i++){
        //     xi = -(Math.log(x[i])/alfa);    /*Math.log(x) é equivalente ao ln(x) da matemática*/
        //     fx = alfa*Math.exp(-(alfa*xi)); /*Math.exp(x) retorna o número de Euler elevado a x, ou seja, e^x*/
        //     pontosDoGrafico.add(xi, fx);
        // }

        // XYDataset datasetXY = new XYSeriesCollection(pontosDoGrafico);
        // GraficoXY graficoPontosXY = new GraficoXY("Variáveis aleatórias com dstribuição exponencial", "x", "f(x)",datasetXY);
        // graficoPontosXY.exibirGraficoEmFrame(800, 640);


        /******** GERAÇÃO DE VARIÁVEIS ALEATÓRIAS COM DISTRIBUIÇÃO WEIBULL *********/
        // XYSeries pontosDoGrafico = new XYSeries("Variáveis aleatórias");
        // double xi = 0;  //xi tem que ser >= 0
        // double fx = 0;
        // double alfa = 3;   //o valor de alfa pode variar
        // double beta = 1;   //o valor de beta pode variar

        // for(i=2; i<semente; i++){
        //     xi = beta*(-(Math.log(Math.pow(x[i], (1/alfa)))));
        //     fx = alfa*(Math.pow(beta,-(alfa)))*Math.pow(xi, (alfa-1))*Math.exp(-(Math.pow((xi/beta), alfa))) ; /*Math.exp(x) retorna o número de Euler elevado a x, ou seja, e^x */
        //     pontosDoGrafico.add(xi, fx);
        // }

        // XYDataset datasetXY = new XYSeriesCollection(pontosDoGrafico);
        // GraficoXY graficoPontosXY = new GraficoXY("Variáveis aleatórias com dstribuição Weibull", "x", "f(x)",datasetXY);
        // graficoPontosXY.exibirGraficoEmFrame(800, 640);


        /******** GERAÇÃO DE VARIÁVEIS ALEATÓRIAS COM DISTRIBUIÇÃO ERLANG *********/
        // XYSeries pontosDoGrafico = new XYSeries("Variáveis aleatórias");
        // double xi = 0;  /*xi tem que ser >= 0*/
        // double fx = 0;
        // int k = 3;       /*o valor de k pode variar e tem que ser > 0*/
        // double alfa = 1;   /*o valor de alfa pode variar e tem que ser > 0*/
        // int j;
        // double somat = 0;
   
        // for(j=2; j<semente; j++){
        //     for(i=2; i<k+2; i++){
        //         somat += (Math.log(x[i])/alfa);
        //     }
        //     xi = -(somat);
        //     fx = (alfa*Math.exp(-(alfa*xi))*Math.pow((alfa*xi), (k-1)))/fatorial(k-1);
        //     pontosDoGrafico.add(xi, fx);
        // }
        // XYDataset datasetXY = new XYSeriesCollection(pontosDoGrafico);
        // GraficoXY graficoPontosXY = new GraficoXY("Variáveis aleatórias com dstribuição Erlang", "x", "f(x)",datasetXY);
        // graficoPontosXY.exibirGraficoEmFrame(800, 640);

        /******** GERAÇÃO DE VARIÁVEIS ALEATÓRIAS COM DISTRIBUIÇÃO NORMAL *********/
        // XYSeries pontosDoGrafico = new XYSeries("Variáveis aleatórias");
        // double xi = 0;
        // double fx = 0;
        // int mi = 0; /*o ponto 'mi', no eixo x, corresponde ao pico do gráfico da distribuição normal*/
        // int sigma = 1;  /*sigma é o desvio padrão de 'mi'*/

        // for(i=2; i<semente-1; i++){ /*semente-1 porque eu preciso pegar sempre dois valores pseudoaleatórios diferentes (x[i] e x[i+1])*/
        //     xi = Math.sqrt(-(2*Math.log(x[i]))) * Math.cos(2*Math.PI*x[i+1]);
        //     fx = Math.exp(-(Math.pow((xi-mi), 2)) / (2*Math.pow(sigma, 2))) / (sigma * Math.sqrt(2*Math.PI));
            
        //     pontosDoGrafico.add(xi, fx);
        // }
        
        // XYDataset datasetXY = new XYSeriesCollection(pontosDoGrafico);
        // GraficoXY graficoPontosXY = new GraficoXY("Variáveis aleatórias com dstribuição normal", "x", "f(x)", datasetXY);
        // graficoPontosXY.exibirGraficoEmFrame(800, 640);


        /******** GERAÇÃO DE VARIÁVEIS ALEATÓRIAS COM DISTRIBUIÇÃO POISSON *********/       
        // XYSeries pontosDoGrafico = new XYSeries("Variáveis aleatórias");
        // int xi;
        // double P;
        // double Px;
        // int lambda = 1;    /*lambda pode variar mas sempre deve ser > 0*/
        // double B = Math.exp(-lambda); 

        // for(i=2; i<semente; i++){
        //     xi = 0;
        //     P = 1;
        //     Px = 0;

        //     do{
        //         P *= x[i];
        //         xi++;
        //     }while(P >= B);
        //     xi--; /*X -- porque eu somo 1 ao X antes de verificar a condição, portanto, quando a condição é aceita, X vai estar valendo uma unidade a mais do que deveria valer*/
                              
        //     Px = ( Math.pow(lambda, xi) * Math.exp(-lambda) ) / fatorial(xi);
            
        //     pontosDoGrafico.add(xi, Px);
        // }
        
        // XYDataset datasetXY = new XYSeriesCollection(pontosDoGrafico);
        // GraficoXY graficoPontosXY = new GraficoXY("Variáveis aleatórias com dstribuição Poisson", "x", "F(X <= x)", datasetXY);
        // graficoPontosXY.exibirGraficoEmFrame(800, 640);


        /******** GERAÇÃO DE VARIÁVEIS ALEATÓRIAS COM DISTRIBUIÇÃO GEOMÉTRICA *********/       
        // XYSeries pontosDoGrafico = new XYSeries("Variáveis aleatórias");
        // double p = 0.2; /*p pode variar entre 0 e 1*/
        // double q = (1-p);
        // int xi;
        // double Px;

        // for(i=2; i<semente; i++){
                       
        //     xi = (int) Math.floor( 1 + (Math.log(x[i])/Math.log(q)) );
        //     Px = p * Math.pow(q, (xi-1));
            
        //     pontosDoGrafico.add(xi, Px);
        // }
        
        // XYDataset datasetXY = new XYSeriesCollection(pontosDoGrafico);
        // GraficoXY graficoPontosXY = new GraficoXY("Variáveis aleatórias com dstribuição geométrica", "x", "P(X=x)", datasetXY);
        // graficoPontosXY.exibirGraficoEmFrame(800, 640);
        

        /**************** SIMULAÇÃO DINÂMICA *****************/       
        int t;
        int leste; /* direção x */
        int norte; /* direção y */
        int j = 2;
        int chegouEmCasa = 0;   /* quantidade de vezes que o bêbado conseguiu chegar em casa dentre as tentativas*/
        int tentativas = 200; /* quantidade de tentativas que o bêbado tem para chegar em casa dentro do tempoMax estipulado */
        int tempoMax = 180;  /* tempo máximo (em min) que o bêbado tem para chegar em casa */

        for(int h = 0; h<tentativas; h++){   
            t = 0;
            leste = 0;
            norte = 0;

            while((t < (tempoMax - 5)) && (leste != 3 || norte != 4)){ /* o bêbado vive na esquina (leste = 3, norte =4), tempoMax-6 porque o bêbado leva, no mínimo, 5 minutos para chegar em casa, portanto, se ele já tem menos de 5 min retantes para tentar, ele não vai conseguir chegar a tempo  */
                if(x[j] <= 0.3){   /* em 30% das vezes, o bêbabdo se detém para refletir e fica parado por 1 minuto*/
                    t++;
                }
                j++;   /* "gera" um novo pseudoaleatório */
                if(x[j] <= 0.35){ /* caminhou para a direção leste */
                    leste++;    
                }else if(x[j] <= 0.8){ /* caminhou para a direção norte */
                    norte++;    
                }else if(x[j] <= 0.9){ /* caminhou para a direção oeste */
                    leste--;
                }else{ /* caminhou para a direção sul */
                    norte--;
                }
                t+=5; /* o bêbado leva, em média, 5 minutos para percorrer uma quadra */
                j++;
            }

            if(leste==3 && norte==4){
                chegouEmCasa++;
            }            
        }

        float percentualDeChegada = (float) chegouEmCasa/tentativas*100;
        
        System.out.printf("\nQuantidade de tentativas para o bêbado chegar em casa: %ds\n", tentativas);
        System.out.printf("Tempo máximo que o bêbado tem para chegar em casa em cada tentativa: %d\n\n", tempoMax);
        System.out.printf("O bêbado chegou em casa em %d das %d tentativas\n", chegouEmCasa, tentativas);
        System.out.printf("Isso equivale a %.2f%% das tentativas\n\n", percentualDeChegada);







    }


    public static void main(String[] args){
        geraNumeros(50000);
    }
}
