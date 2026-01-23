package logicaDprogrmacao;
import java.util.Scanner;




public class CadastroDeCPF {
	static int contaCPFcadastrados = 0; 
	
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
		System.out.println("______________________________________");
		System.out.println("___________Escolha uma opção__________");
		System.out.println("----Digite 0- Encerrar----------------");
		System.out.println("----Digite 1- Cadastrar nome e cpf----");
		System.out.println("----Digite 2- Buscart CPF ------------");
		System.out.println("----Digite 3- Editar nome cadastrado--");
		System.out.println("----Digite 4- Excluir cadastrado------");
		System.out.println("----Digite 5- Exibir todos -----------");
		System.out.println("______________________________________");
		return Integer.parseInt(lerDados(""));
	}
	public static String [][] cadastrarUsusario(String usuarios[][]) {
		if(!existeEspacoNaBaseDedados(usuarios)) {
			System.out.println("base de dados cheia, não é possivel cadastrar");
			return usuarios;
		}
		
		String cpf =lerDados("Digite o CPF");
		
		if (!cpfValido(cpf)){
			System.out.println("CPF inválido digite um nuvo cpf");
			return usuarios;
		}
		
		if(usuarioCadastrado(usuarios,cpf)) {
			System.out.println("usuario ja cadastrado");
			return usuarios;
		}
		
		String nome = lerDados("Digite o nome");
		for(int i = 0;i<usuarios.length;i++) {
			String usuario[] = usuarios[i];
			if(usuario[0] == null) {
				usuarios[i] = new String[2];
				usuarios[i][0]=cpf;
				usuarios[i][1]=nome;
	
				break;
			}
		}
		contaCPFcadastrados ++;
		return usuarios;
	}
	public static boolean existeEspacoNaBaseDedados(String usuarios[][]) {
		int maximoArmazenamento = 3;
		return contaCPFcadastrados < maximoArmazenamento;
				
	}
	public static boolean cpfValido(String cpf) {
		if (cpf.length() < 11) return false;
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
		if(somaMultiplicacao < 2 && Character.getNumericValue(cpf.charAt(9))==0) {
			return true;
			
		}else if(somaMultiplicacao >= 2 && (11-somaMultiplicacao )== Character.getNumericValue(cpf.charAt(9))){
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
		if(somaMultiplicacao < 2 && Character.getNumericValue(cpf.charAt(10))==0) {
			return true;
			
		}else if(somaMultiplicacao >= 2 && (11-somaMultiplicacao )== Character.getNumericValue(cpf.charAt(10))){
			return true;
		}else {
			return false;
		}
	}
	public static String lerDados (String mensagem) {
		Scanner scanner = new Scanner(System.in);
		String input;
		System.out.println(mensagem);
		input = scanner.nextLine();
		
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
				System.out.printf("cpf: %s nome: %s \n", usuario[0],usuario[1]);
				return;
			}
		}
		System.out.println("usuario não cadastrado");
	}
	public static void editarUsuario(String usuarios[][]) {
				
		String cpf =lerDados("Digite o cpf do usuario que deseja editar o nome");
		for (int i=0;i < usuarios.length;i++) {
			String[] usuario = usuarios[i];
			if(cpf.equals(usuario[0])) {
				System.out.printf("o nome cadastrado para este CPF é %s\n",usuario[1]);
				usuarios[i][1] = lerDados("Qual nome irá subistitui-lo");
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
					contaCPFcadastrados --;
				}
			}
		}
		
		
	
	}
	public static void exibirTodosUsuarios(String usuarios[][] ) {
		
		for(int i =0; i< usuarios.length;i++) {
			String[] usuario = usuarios[i];
			int contaNulos =0;
			if(usuario[0] != null) {
				System.out.printf("CPF ->%s",usuario[0]);
				System.out.printf(" Nome->%s\n",usuario[1]);
				
			}else {
				contaNulos ++;
			}
			
			if( contaNulos ==3 ){
				System.out.println("Nenhum cadastro encontrado!!");
			}
		}
		
	}
}
