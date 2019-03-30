package graphtheoryjava;

public class Texto {
	String Txt(int opcao) {
            String s;

		switch (opcao) {
		case 0:
			s = "toy.txt";
			break;
		case 1:
			s = "queen8_8.txt";
			break;
		case 2:
			s = "miles1000.txt";
			break;
		case 3:
			s = "mulsol.i.1.txt";
			break;
		case 4:
			s = "school1.txt";
			break;
		case 5:
			s = "DSJC1000.5.txt";
			break;
		
		default:
			return null;
		}

		return s;
	}

}
