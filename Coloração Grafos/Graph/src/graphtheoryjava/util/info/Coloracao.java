package graphtheoryjava.util.info;
import graphtheoryjava.util.Grafo;
import java.util.ArrayList;
import java.util.Random;

public class Coloracao {

    public Grafo grafo;

    public Coloracao(Grafo p_grafo) {
        this.grafo = p_grafo;
    }
    public Info info = new Info(grafo);
    
    public ArrayList<Integer> Coloracao(Grafo g){
        
        ArrayList<Integer> S = new ArrayList();
        ArrayList<Integer> Cor = new ArrayList();
        
        for(int i=0;i<grafo.vertices.size();i++){
            Cor.add(i);
            S.add(i);
        }
       
        for (int u=0;u<grafo.vertices.size();u++) {
            ArrayList<Integer>CoresPossiveis = new ArrayList();
            for(int i=0;i<Cor.size();i++){
                CoresPossiveis.add(Cor.get(i));
            }
            for(int i=0;i<Cor.size();i++){
                ArrayList<Integer> VAdjS = new ArrayList();
		for (int j = 0; j < grafo.vertices.size(); j++) {
			if (grafo.adjMatrix[u][j] != 0)
                		VAdjS.add(j);
		}
                for(int v=0;v<VAdjS.size();v++){
                    if(S.get(VAdjS.get(v))==i && Pertence(i,CoresPossiveis))
                        CoresPossiveis.remove(CoresPossiveis.indexOf(i));
                }
                S.set(u, CoresPossiveis.get(0));          
            }
        }
        return S;
    }
    private boolean Pertence(int v,ArrayList<Integer> Cores){
        for(int i=0;i<Cores.size();i++){
            if(Cores.get(i)==v){
                return true;
            }
        }
        return false;
    }
    
    public ArrayList<Integer> Refinamento(ArrayList<Integer> S, Grafo g) {

        ArrayList<Integer> Cores = new ArrayList();
        int u, cor, control = 0;
        Cores = CoresUtilizadas(S, Cores);

        long start = System.currentTimeMillis();
        long stop = start + 100000;

        while (start < stop) {
            ArrayList<Integer> auxCores = new ArrayList();
            Random aleatorio = new Random();
            u = aleatorio.nextInt(g.vertices.size());
            cor = aleatorio.nextInt(Cores.size() - 1);
            ArrayList<Integer> auxS = new ArrayList();

            auxS.clear();
            auxS.addAll(S);
            auxS.set(u, cor);

            ArrayList<Integer> Sucessores = new ArrayList();
            for (int j = 0; j < grafo.vertices.size(); j++) {
                if (grafo.adjMatrix[u][j] != 0)
                    Sucessores.add(j);
            }

            for (int i = 0; i < Sucessores.size(); i++) {
                if (auxS.get(Sucessores.get(i)) != cor){
                    control = 1;
                }else{
                    control = 0;
                    break;
                }
            }

            if (control == 1) {
                for (int j = 0; j < Sucessores.size(); j++) {
                    if (auxS.get(Sucessores.get(j)) != cor) {
                        auxCores = CoresUtilizadas(auxS, auxCores);
                        if (auxCores.size() <= Cores.size()) {
                            S.clear();
                            S.addAll(auxS);
                            Cores.clear();
                            Cores.addAll(auxCores);
                        }
                    }
                }
            }
            start = System.currentTimeMillis();
        }
        return S;
    }
    
    private ArrayList<Integer> CoresUtilizadas(ArrayList<Integer> S, ArrayList<Integer> cores) {
        int maior = S.get(0), aux = 0;
        for(int i = 0; i < S.size(); i++){
            if(maior < S.get(i)){
                maior = S.get(i);
            }
        }
        for(int i = 0; i <= maior ; i++){
            cores.add(aux);
            aux++;
        }
        return cores;
    }

}
