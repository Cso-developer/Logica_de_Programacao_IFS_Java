package logicaDprogrmacao;
import java.util.Scanner;




public class CadastroDeCPF {
	
	
	public static void main(String[] args) {
		int opcao=0;
		String[][] usuarios =new String[3][2];
		
		do {
			opcao = menuInicial();
			switch(opcao) {
				case 1:{
					usuarios = cadastrarUsusario(usuarios);
					break;
				}
				
				case 2: {	
					exibirUsuario(usuarios);
					break;
				}
						
				case 3: {
					editarUsuario(usuarios);
					break;
				}
				case 4: {
					apagarUsuario(usuarios);
					break;
				}
				case 5: {
					exibirTodosUsuarios(usuarios);
					break;
				}
					
					
				
			}
			
		}while(opcao !=0);
	
		
	}
	
	public static int menuInicial() {
		Scanner scanner = new Scanner(System.in);
		int opcao=0;
		System.out.println("______________________________________");
		System.out.println("___________Escolha uma opção__________");
		System.out.println("----Digite 0- Encerrar----------------");
		System.out.println("----Digite 1- Cadastrar nome e cpf----");
		System.out.println("----Digite 2- Buscart CPF ------------");
		System.out.println("----Digite 3- Editar nome cadastrado--");
		System.out.println("----Digite 4- Excluir cadastrado------");
		System.out.println("----Digite 5- Exibir todos -----------");
		System.out.println("______________________________________");
		opcao= scanner.nextInt();
		return opcao;
	}
	public static String [][] cadastrarUsusario(String usuarios[][]) {
		if(!existeEspacoNaBaseDedados(usuarios)) {
			System.out.println("base de dados cheia, não é possivel cadastrar");
			return usuarios;
		}
		
		String cpf =lerDados("Digite o CPF");
		
		if (!cpfValido(cpf)){
			System.out.println("CPF inválido");
			return usuarios;
		}
		
		if(usuarioCadastrado(usuarios,cpf)) {
			System.out.println("usuario ja cadastrado");
			return usuarios;
		}
		
		String nome = lerDados("Digite o nome");
		for(int i = 0;i<usuarios.length;i++) {
			String[] usuario = usuarios[i];
			if(usuario[0] == null) {
				usuarios[i] = new String[2];
				usuarios[i][0]=cpf;
				usuarios[i][1]=nome;
				break;
			}
		}
		
		return usuarios;
	}
	public static boolean existeEspacoNaBaseDedados(String usuarios[][]) {
		int tamanhoMaximo = 3;
		return usuarios.length == tamanhoMaximo;
	}
	public static boolean cpfValido(String cpf) {
		boolean digitoVerificador1 = validarVerificador1(cpf);
		boolean digitoVerificador2 = validarVerificador2(cpf);
		boolean cpfValido= false ;
		if (digitoVerificador1 ==true && digitoVerificador2==true) {
			cpfValido = true;
		}else {
			System.out.println("CPF inválido");
		}
		
		return cpfValido;
		
	}
	public static boolean validarVerificador1(String cpf) {
		int multiplicador = 10;
		int somaMultiplicacao = 0;
		
		
		for(int i =0;i< cpf.length()-2;i++) {
			somaMultiplicacao= somaMultiplicacao+(Character.getNumericValue(cpf.charAt(i))*multiplicador);
			multiplicador = multiplicador-1;
			
		}
		somaMultiplicacao = somaMultiplicacao %11;
		if(somaMultiplicacao < 2 && cpf.charAt(9)==0) {
			return true;
			
		}else if(somaMultiplicacao > 2 && (11-somaMultiplicacao )== Character.getNumericValue(cpf.charAt(9))){
			return true;
		}else {
			return false;
		}
			
		
		
	}
	public static boolean validarVerificador2(String cpf) {
		int multiplicador = 11;
		int somaMultiplicacao = 0;
		
		
		for(int i =0;i< cpf.length()-1;i++) {
			somaMultiplicacao= somaMultiplicacao+(Character.getNumericValue(cpf.charAt(i))*multiplicador);
			multiplicador = multiplicador-1;
			
		}
		somaMultiplicacao = somaMultiplicacao %11;
		if(somaMultiplicacao < 2 && cpf.charAt(9)==0) {
			return true;
			
		}else if(somaMultiplicacao > 2 && (11-somaMultiplicacao )== Character.getNumericValue(cpf.charAt(10))){
			return true;
		}else {
			return false;
		}
	}
	public static String lerDados (String mensagem) {
		Scanner scanner = new Scanner(System.in);
		String input;
		System.out.println(mensagem);
		input = scanner.next();
		
		return input;
	}
	public static boolean usuarioCadastrado(String usuarios[][], String cpf) {
		for(int i = 0;i<usuarios.length;i++) {
			String[] usuario = usuarios[i];
			if(usuario[0] != null && usuario[0].equals(cpf)) {
				return true;
			}
			
		}
	return false;	
	}
	public static void exibirUsuario(String usuarios[][]) {
		String cpf = lerDados("Digite o cpf");
		for(int i = 0;i < usuarios.length;i++) {
			String[] usuario = usuarios[i];
			if(usuario[0] != null && usuario[0].equals(cpf)) {
				System.out.printf("cpf: %s nome: %s ", usuario[0],usuario[1]);
				break;
			}else {
				System.out.println("usuario não cadastrado");
				break;
			}
		}
		
	}
	public static void editarUsuario(String usuarios[][]) {
		Scanner scanner = new Scanner(System.in);
				
		String cpf =lerDados("Digite o cpf do usuario que deseja editar o nome");
		for (int i=0;i < usuarios.length;i++) {
			String[] usuario = usuarios[i];
			if(cpf.equals(usuario[0])) {
				System.out.printf("o nome cadastrado para este CPF é %s\n",usuario[1]);
				System.out.println("Qaul nome irá substitui-lo?");
				usuarios[i][1] = scanner.next();
			}
		}
	}
	public static void apagarUsuario(String usuarios[][]) {
		Scanner scanner = new Scanner(System.in);
		String cpf =lerDados("Digite o numero do CPF que será excluido");
		for (int i = 0 ;i< usuarios.length;i++) {
			String[] usuario = usuarios[i];
			if(cpf.equals(usuario[0])) {
				System.out.printf("O usuario--> %s CPF--> %s será excluido",usuario[1],usuario[0]);
				String deletar = "n";
				System.out.println("Tem certeza que deseja deletar este usuário?\ndigite s parea deletar ou n para não");
				deletar =scanner.next();
				if (deletar.equals("s")) {
					usuarios[i][1]= null;
					usuarios[i][0]= null;
				}
			}
		}
		
		
	
	}
	public static String exibirTodosUsuarios(String usuarios[][] ) {
		
		for(int i =0; i< usuarios.length;i++) {
			String[] usuario = usuarios[i];
			int contaNulos =0;
			if(usuario[i] != null) {
				System.out.println(usuario[i]);
				
			}else {
				
			}
		}
		return "";
	}
}
