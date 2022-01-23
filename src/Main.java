import java.lang.Math;

public class Main {    
     
    /**********************************************************************
    * Cálculo da média do array de médias
    **********************************************************************/ 
    public static float media(float[] arrMedias){
        float somaMedias = 0;
        int i;

        for(i=2; i<arrMedias.length; i++){
            somaMedias += arrMedias[i];
        }
        return (somaMedias/(arrMedias.length-2));
    }
    /*********************************************************************/


    /**********************************************************************
    * Cálculo do desvio padrão do array de médias
    **********************************************************************/ 
    public static float desvioPadrao(float[] arrMedias, float mediaDasMedias){
        int n = (arrMedias.length - 2);
        int i;
        float desvPad = 0;
        float S_XiMedQuad = 0;

        for(i=0; i< arrMedias.length; i++){
            S_XiMedQuad += Math.pow( (arrMedias[i] - mediaDasMedias), 2 );
        }

        desvPad = (float) Math.sqrt( (S_XiMedQuad/(n-1)) );

        return (float) desvPad;
    }
    /*********************************************************************/


    /**********************************************************************
    * Intervalo de confiança
    **********************************************************************/ 
    public static float[] intervConf(float mediaDasMedias, int qtdClientes, float desvPadr){
        float[] interv = new float[2];
        int n = qtdClientes;

        //limite superior
        interv[0] = (float) ( mediaDasMedias + (1.96 * ( desvPadr / Math.sqrt(n))) );

        //limite inferior
        interv[1] = (float) ( mediaDasMedias - (1.96 * ( desvPadr / Math.sqrt(n))) );

        return interv;
    }
    /*********************************************************************/


    /**********************************************************************
    * Gera o array de pseudoaleatorios para cada tabela de clientes (um valor para cada cliente)
    **********************************************************************/
    public static float[] geraRand(int clientes, int semente){
        final int a = 16807;  
        final int M = Integer.MAX_VALUE;
        float[] rand = new float[clientes]; /*armazena os valores pseudoaleatórios gerados*/

        long w = semente;
        int i = 0;
        
        for(i=0; i<clientes; i++){ 
            rand[i] = (float) w/M;  /*w/M para gerar o valor entre 0 e 1*/
            w = (a * w) % M;
            // System.out.println("rand[i]: "+rand[i]);
        } 
        return rand;
    }
    /*********************************************************************/


    public static void main(String[] args){
        int qtdtestes = 100000;  //(qtdTestes = rodadas) para cada semente eu gero uma tabela de clientes 
        int qtdClientes = 1000;    //(qtdClientes = replicações)
        int semente = 10;   //para cada teste eu preciso usar uma semente diferente e a mesma quantidade de clientes

        float[] seedsClientes = new float[qtdClientes];  //array com os pseudoaleatorios
        int i;
        float[] medTpoFila = new float[qtdtestes]; //array das médias dos tempos na fila
        float[] medTpoServ = new float[qtdtestes]; //array das médias dos tempos no serviço
        float[] medTpoSist = new float[qtdtestes]; //array das médias dos tempos no sistema


        for(i=0; i<qtdtestes; i++){ //para cada teste é gerada uma tabela com a quantidade de clientes especificada e seus respectivos TEC, TS e médias
            /**********************************************************************
            * Gera o array com um valor randomico para cada cliente - descontar os 
            * dois primeiros valores gerados - a cada teste é usada uma semente
            * de geração de numeros aleatórios diferente e mesmos parâmetros de 
            * entrada.
            **********************************************************************/
            seedsClientes = geraRand(qtdClientes, semente+i);
            /*********************************************************************/


            /**********************************************************************
            * Gera os TEC e TS
            **********************************************************************/ 
            float[] TEC = new float[qtdClientes];  /* Tempo entre chegadas */
            float[] TS = new float[qtdClientes];   /* Tempo de espera */
            int j;

            TEC[0] = 0; /*como eu vou descartar os dois primeiros valores de TEC e TES eu aproveito para zerá-los para poder fazer os próximos cálculos, na verdade, eu só precisaria zerar TEC[1] e TS[1], mas já zerei as duas primeiras posições para ficar melhor*/
            TEC[1] = 0;
            TS[0] = 0;
            TS[1] = 0;

            for(j=2; j<seedsClientes.length; j++){
                /* LEMBRAR DE IGNORAR OS DOIS PRIMEIROS TEC E TS */
                TEC[j] = (float) -(2 * Math.log(seedsClientes[j]));
                TS[j] = (float) -(3 * Math.log(seedsClientes[j])); 
                // System.out.println("TEC: "+TEC[j]);
                // System.out.println("TS: "+TS[j]);
            }   
            /*********************************************************************/


            /**********************************************************************
            * Calculo do tempo de chegada no relógio
            **********************************************************************/ 
            float[] TCR = new float[seedsClientes.length];
            TCR[0] = 0;   
            TCR[1] = 0; /*como eu vou descartar os dois primeiros valores de TEC e TS e eu preciso de um TCR inicial = 0 para calcular o resto dos tempos de chegada no relógio, eu estou usando TCR[1] para isso, TRC[0] eu tambem nao uso para os arrays começarem todos em i=2*/
            
            for(j=2; j<seedsClientes.length; j++){
                TCR[j] = TCR[j-1]+TEC[j];
                // System.out.println("TCR: "+TCR[j]);
            }
            /*********************************************************************/


            /**********************************************************************
            * Calculo do tempo de inicio do serviço
            **********************************************************************/ 
            float[] TIS = new float[seedsClientes.length];
            TIS[0] = 0;   
            TIS[1] = 0; //mesmo caso do cálculo de chegada no relógio
            
            for(j=2; j<seedsClientes.length; j++){
                if((TIS[j-1] + TS[j-1]) < TCR[j]) {
                    TIS[j] = TCR[j];
                }else{
                    TIS[j] = (TIS[j-1] + TS[j-1]);
                }
                // System.out.println("TIS: "+TIS[j]);
            }
            /*********************************************************************/
           

            /**********************************************************************
            * Calculo dos tempos na fila e média dos tempos na fila
            **********************************************************************/ 
            float[] TF = new float[seedsClientes.length];
            float tpoMedFila = 0;

            TF[0] = 0;   
            TF[1] = 0; //mesmo caso do cálculo de chegada no relógio
            
            for(j=2; j<seedsClientes.length; j++){
                TF[j] = TIS[j]-TCR[j];
                // System.out.println("TF: "+TF[j]);
            }

            tpoMedFila = media(TF);
            // System.out.println("média de tempo na fila: "+tpoMedFila);
            /*********************************************************************/


            /**********************************************************************
            * Calculo dos tempos no sistema e média dos tempos no sistema
            **********************************************************************/ 
            float[] TSI = new float[seedsClientes.length];
            float tpoMedSist = 0;

            TSI[0] = 0;   
            TSI[1] = 0; //nao precisaria zerar os dois primeiros elementos desse array, só fiz isso para padronizar com os outros

            for(j=2; j<seedsClientes.length; j++){
                TSI[j] = TS[j]+TF[j];   
                // System.out.println("tempo no sistema: "+TSI[j]);
            }

            tpoMedSist = media(TSI);
            // System.out.printf("média de tempo no sistema (teste %d): %f\n",i,tpoMedSist);
            /*********************************************************************/


            /**********************************************************************
            * Calculo dos tempos médios de serviço
            **********************************************************************/
            float tpoMedServ = 0;

            tpoMedServ = media(TS);
            // System.out.printf("média de tempo no serviço (teste %d): %f\n",i,tpoMedServ);   
            /*********************************************************************/
            

            /**********************************************************************
            * Armazena o resultado dos tempos médios de cada teste
            **********************************************************************/
            medTpoFila[i] = tpoMedFila; //aqui é i mesmo, não é j porque eu quero pegar o resultado de cada teste
            medTpoServ[i] = tpoMedServ; //aqui é i mesmo, não é j porque eu quero pegar o resultado de cada teste
            medTpoSist[i] = tpoMedSist; //aqui é i mesmo, não é j porque eu quero pegar o resultado de cada teste
            /*********************************************************************/
        }


        /**********************************************************************
        * Calculo da média das médias
        **********************************************************************/
        float medTpoMedFila = media(medTpoFila);    //média das médias de tempo na fila
        float medTpoMedServ = media(medTpoServ);    //média das médias de tempo no serviço
        float medTpoMedSist = media(medTpoSist);    //média das médias de tempo no sistema

        System.out.println("media das medias de tempo na fila: "+medTpoMedFila);
        System.out.println("media das medias de tempo de serviço: "+medTpoMedServ);   
        System.out.println("media das medias de tempo no sistema: "+medTpoMedSist);
        /*********************************************************************/
        

        /**********************************************************************
        * Calculo do desvio padrão
        **********************************************************************/
        float desvPadFila = desvioPadrao(medTpoFila, medTpoMedFila);
        float desvPadServ = desvioPadrao(medTpoServ, medTpoMedServ);
        float desvPadSis = desvioPadrao(medTpoSist, medTpoMedSist);

        System.out.println("desvio padrão do tempo na fila: "+desvPadFila);
        System.out.println("desvio padrão do tempo de serviço: "+desvPadServ);
        System.out.println("desvio padrão do tempo no sistema: "+desvPadSis);
        /*********************************************************************/


        /**********************************************************************
        * Calculo do intervalo de confiança
        **********************************************************************/
        float[] limitesFila = intervConf(medTpoMedFila, qtdClientes, desvPadFila);
        float[] limitesServ = intervConf(medTpoMedServ, qtdClientes, desvPadServ);
        float[] limitesSist = intervConf(medTpoMedSist, qtdClientes, desvPadSis);

        System.out.println("intervalo de confiança da média do tempo médio na fila: ["+limitesFila[0]+", "+limitesFila[1]+"]");
        System.out.println("intervalo de confiança da média do tempo médio de serviço: ["+limitesServ[0]+", "+limitesServ[1]+"]");
        System.out.println("intervalo de confiança da média do tempo médio no sistema: ["+limitesSist[0]+", "+limitesSist[1]+"]");
        /*********************************************************************/
    }
}