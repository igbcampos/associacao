package botAssociacao;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.TimeZone;
import java.util.regex.Pattern;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardRemove;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import org.telegram.telegrambots.exceptions.TelegramApiRequestException;

public class MinhaAssociacaoBot extends TelegramLongPollingBot {

	public static ArrayList<TelegramFlags> flags = new ArrayList<TelegramFlags>();
	public static int entendeu = 0;
	public static boolean pagamento = false;
	public static boolean presenca = false;
	
	@Override
	public String getBotUsername() {
		return "MinhaAssociacao";
	}

	public Associado proprietarioTelegramId(long telegramId) {
		DAOAssociado daoAssociado = new DAOAssociado();
	
		Associado associado = daoAssociado.buscarTelegramId(telegramId);

		if(associado == null) {
			associado = daoAssociado.buscarTelegramId(393558363);
		}
		
		return associado;
	}
	
	/*public void pedirCodigoAssociado(Update u) {
		SendMessage send = new SendMessage();
		send.setChatId(u.getMessage().getChatId());
		String message = u.getMessage().getText()
	}*/
	
	/*public void converteDatas(String texto) {
		GregorianCalendar gc = new GregorianCalendar();
				
		int barra = texto.indexOf("/");
		int dia = Integer.parseInt(texto.substring(0, barra));
		texto = texto.substring(barra+1, texto.length());
		
		barra = texto.indexOf("/");
		int mes = Integer.parseInt(texto.substring(0, barra));
		texto = texto.substring(barra+1, texto.length());
		
		barra = texto.indexOf(" ");
		int ano = Integer.parseInt(texto.substring(0, barra));
		texto = texto.substring(barra+1, texto.length());
		
		gc.set(ano, mes - 1, dia);
		long inicio = gc.getTimeInMillis();
		
		barra = texto.indexOf("/");
		dia = Integer.parseInt(texto.substring(0, barra));
		texto = texto.substring(barra+1, texto.length());
		
		barra = texto.indexOf("/");
		mes = Integer.parseInt(texto.substring(0, barra));
		texto = texto.substring(barra+1, texto.length());
		
		barra = texto.indexOf(" ");
		ano = Integer.parseInt(texto.substring(0, texto.length()));
		
		gc.set(ano, mes - 1, dia);
		long fim = gc.getTimeInMillis();
		
	}*/
	
	public String consultarFrequencia(long telegramId, String texto) {
		int flag;
		int aux = 0;
		String message = "Registro de frequência:\n\n";
		DAOReuniao daoReuniao = new DAOReuniao();
		DAOFrequencia daoFrequencia = new DAOFrequencia();
		Associado associado = proprietarioTelegramId(telegramId);
		InterfaceAssociacao controle = new MinhaAssociacao();
		
		//era pra ser uma função que formata a data recebida como string, mas eu não
		//sei retornar duas coisas de uma vez, com um vetor talvez, mas não acho tão legal
		GregorianCalendar gc = new GregorianCalendar();
		
		int barra = texto.indexOf("/");
		int dia = Integer.parseInt(texto.substring(0, barra));
		texto = texto.substring(barra+1, texto.length());
		
		barra = texto.indexOf("/");
		int mes = Integer.parseInt(texto.substring(0, barra));
		texto = texto.substring(barra+1, texto.length());
		
		barra = texto.indexOf(" ");
		int ano = Integer.parseInt(texto.substring(0, barra));
		texto = texto.substring(barra+1, texto.length());
		
		gc.set(ano, mes - 1, dia);
		long inicio = gc.getTimeInMillis();
		
		barra = texto.indexOf("/");
		dia = Integer.parseInt(texto.substring(0, barra));
		texto = texto.substring(barra+1, texto.length());
		
		barra = texto.indexOf("/");
		mes = Integer.parseInt(texto.substring(0, barra));
		texto = texto.substring(barra+1, texto.length());
		
		barra = texto.indexOf(" ");
		ano = Integer.parseInt(texto.substring(0, texto.length()));
		
		gc.set(ano, mes - 1, dia);
		long fim = gc.getTimeInMillis();
		//a parte da formatação acaba aqui
		
		if(associado != null) {
			//trecho que vai formatar o long em date
			DateFormat df = new SimpleDateFormat ("dd/MM/yyyy");
			df.setTimeZone(TimeZone.getTimeZone ("GMT"));
			
			ArrayList<Long> reunioes = daoReuniao.reunioesExistentes(associado.getAssociacao(), inicio, fim);
			ArrayList<Long> frequencias = daoFrequencia.frequenciasRegistradas(associado.getNumero(), associado.getAssociacao(), inicio, fim);
			
			for(Long reuniao : reunioes) {
				flag = 0;
				for(Long frequencia : frequencias) {
					if(reuniao.equals(frequencia)) {
						message += df.format(reuniao) + ": Presente\n";
				        flag = 1;
					}
				}
				if(flag == 0) {
					message += df.format(reuniao) + ": Ausente\n";
				}
				aux++;
			}
			if(aux == 0) {
				message += "Não foram encontradas reuniões/frequências no período solicitado.";
			}
			else {
				try {
					message += "\nPresença total: " + controle.calcularFrequencia(associado.getNumero(), associado.getAssociacao(), inicio, fim) * 100 + "%";
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		else {
			message += "Você precisa fazer login para ter acesso as suas informações.\n";
		}
		
		return message;
	}
	
	public String consultarPagamentos(Associado associado, int ano) {
		int flag;
		int aux = 0;
		double valorPago;
		String message = "Registro de pagamentos " + ano + ":\n\n";
		DAOTaxa daoTaxas = new DAOTaxa();
		DAOPagamento daoPagamentos = new DAOPagamento();
		
		if(associado != null) {
			//trecho que vai formatar o long em date
			DateFormat df = new SimpleDateFormat ("dd/MM/yyyy");
			df.setTimeZone(TimeZone.getTimeZone ("GMT"));
			
			ArrayList<Taxa> taxas = daoTaxas.taxasExistentes(associado.getAssociacao(), ano);
			ArrayList<Pagamento> pagamentos = daoPagamentos.pagamentosRealizados(associado.getAssociacao(), associado.getNumero(), ano);
			
			for(Taxa taxa : taxas) {
				if(aux != 0) {
					message += "\n              -------------------------\n\n";
				}
				
				message += "Taxa: " + taxa.getNome() + " | Valor: R$" + taxa.getValorAno() + "\n\n";
				flag = 0;
				valorPago = 0;
				
				for(Pagamento pagamento : pagamentos) {
					if(taxa.getNome().equals(pagamento.getNomeTaxa()) && (taxa.getVigencia() == pagamento.getVigencia())) {
						message += "  " + df.format(pagamento.getData()) + ": R$" + pagamento.getValor() + "\n";
						valorPago += pagamento.getValor();
				        flag = 1;
					}
				}
				if(taxa.getAdministrativa() && associado instanceof AssociadoRemido) {
					message += "  Associado remido!\n";
				}
				else if(taxa.getValorAno() == valorPago) {
					message += "\n  Taxa quitada.\n";
				}
				else if(flag != 0) {
					message += "\n  Total pago: R$" + valorPago + "\n  Falta pagar: R$" + (taxa.getValorAno() - valorPago) + ".\n";
				}
				else {
					message += "  Não houve pagamentos.\n  Falta pagar: R$" + taxa.getValorAno() + ".\n";
				}
				aux++;
			}
			if(aux == 0) {
				message += "Não foram encontradas taxas/pagamentos no período solicitado.\n";
			}
		}
		else {
			message += "Você precisa fazer login para ter acesso as suas informações.\n";
		}
		
		return message;
	}
	
	@Override
	public void onUpdateReceived(Update u) {
		SendMessage send = new SendMessage();
		send.setChatId(u.getMessage().getChatId());
		
		String nome = u.getMessage().getFrom().getFirstName();
		long telegramId = u.getMessage().getChatId();
		String message = u.getMessage().getText();
		System.out.println(nome + " (" + telegramId + "): " + message);
		
		Associado associado = proprietarioTelegramId(telegramId);
		String resposta;
		int aux = 0;
		
		//variavel TelegramFlags auxiliar
		TelegramFlags telegramFlags = new TelegramFlags();;
		
		//percorrendo a lista de flags que contem as flags para um id telegram especifico buscando o conjunto de flags para um id telegram
		for(TelegramFlags telegramFlag : flags) {
			if(telegramFlag.telegramId == telegramId) {
				telegramFlags = telegramFlag;
				aux++;
			}
		}
		//se eu não enontrar um conjunto de flags para aquele id telegram, eu vou criar um novo conjunto de flags
		if(aux == 0) {
			telegramFlags.telegramId = telegramId;
			flags.add(telegramFlags);
		}
		
		if(telegramFlags.pagamento) {
			if(message.matches("\\d\\d\\d\\d")) {
				//resposta = "você digitou um ano válido";
				int ano = Integer.parseInt(message);
				resposta = consultarPagamentos(associado, ano);
			}
			else {
				resposta = "Você não digitou um ano válido. Por favor, consulte novamente.";
			}
			telegramFlags.pagamento = false;
		}
		else if(telegramFlags.presenca) {
			if(message.matches("\\d\\d/\\d\\d/\\d\\d\\d\\d \\d\\d/\\d\\d/\\d\\d\\d\\d")) {
				//resposta = "você digitou uma data válida";
				//converteDatas(message);
				resposta = consultarFrequencia(telegramId, message);
			}
			else {
				resposta = "Você não digitou uma data válida. Por favor, consulte novamente.";
			}
			telegramFlags.presenca = false;
		}
		else if(message.equals("/start")) {
			if(associado != null) {
				resposta = "Olá " + nome + ", para ter acesso aos seus dados na associação basta perguntar sobre as suas taxas ou a sua frequência nas reuniões. Você também pode usar o comando /consultar. Em caso de dúvida use o comando /help.";
			}
			else {
				resposta = "Olá, para ter acesso aos seus dados na associação basta perguntar sobre as suas taxas ou a sua frequência nas reuniões. Você também pode usar o comando /consultar. Em caso de dúvida use o comando /help.";
			}
			telegramFlags.entendeu = 0;
		}
		else if(message.equals("/help")) {
			resposta = "Você pode perguntar Em quais reunioes estive presente, por exemplo, para exibir sua frequência, assim como, Quais taxas preciso pagar, para mostra as informações sobre suas taxas. Além disso, você pode usar o comando /consultar.";
			telegramFlags.entendeu = 0;
		}
		else if(message.equals("/consultar") || message.contains("Consulta") || message.contains("consulta")) {
			resposta = "O que você deseja consultar?";
			
			// Create ReplyKeyboardMarkup object
			ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
			// Create the keyboard (list of keyboard rows)
			List<KeyboardRow> keyboard = new ArrayList<>();
			// Create a keyboard row
			KeyboardRow row = new KeyboardRow();
			// Set each button, you can also use KeyboardButton objects if you need something else than text
			row.add("Reuniões");
			// Add the first row to the keyboard
			keyboard.add(row);
			// Create another keyboard row
			row = new KeyboardRow();
			// Set each button for the second line
			row.add("Taxas");
			// Add the second row to the keyboard
			keyboard.add(row);
			// Set the keyboard to the markup
			keyboardMarkup.setKeyboard(keyboard);
			// Adjust the size of the keyboard so it fits the buttons
			keyboardMarkup.setResizeKeyboard(true);
			// Add it to the message
			send.setReplyMarkup(keyboardMarkup);
			telegramFlags.entendeu = 0;
		}
		else if(message.contains("pag") || message.contains("Pag") || message.contains("Taxa") || message.contains("taxa")) {
			if((!message.contains("não")) && (!message.contains("Não")) && (!message.contains("nao")) && (!message.contains("Nao"))) {
				telegramFlags.pagamento = true;
				resposta = "Qual ano você gostaria de consultar? Use o formato aaaa (exemplo 2018).";
				//resposta = consultarPagamentos(telegramId);
			}
			else {
				resposta = "Acho que você não quer saber sobre isso.";
			}
			ReplyKeyboardRemove keyboardMarkup = new ReplyKeyboardRemove();
		    send.setReplyMarkup(keyboardMarkup);
		    telegramFlags.entendeu = 0;
		}
		else if(message.contains("frequên") || message.contains("Frequên") || message.contains("frequen") || message.contains("Frequen") 
				|| message.contains("Reuni") || message.contains("reuni") || message.contains("Falt") || message.contains("falt") || message.contains("Presen") || message.contains("presen")) {
			if((!message.contains("não")) && (!message.contains("Não")) && (!message.contains("nao")) && (!message.contains("Nao"))) {
				telegramFlags.presenca = true;
				resposta = "Que período você gostaria de consultar? Use o formato dd/mm/aaaa dd/mm/aaaa (exemplo 01/03/2017 30/06/2017).";
				//resposta = consultarFrequencia(telegramId);
			}
			else {
				resposta = "Acho que você não quer saber sobre isso.";
			}
			ReplyKeyboardRemove keyboardMarkup = new ReplyKeyboardRemove();
		    send.setReplyMarkup(keyboardMarkup);
		    telegramFlags.entendeu = 0;
		}
		else if(message.contains("Muito obrigado") || message.contains("muito obrigado") || message.contains("Obrigado") || message.contains("obrigado")) {
			resposta = "De nada.";
			telegramFlags.entendeu = 0;
		}
		else if(telegramFlags.entendeu == 0){
			resposta = "O que você gostaria de saber?";
			telegramFlags.entendeu++;
		}
		else {
			resposta = "Eu não entendi. Você poderia repetir, por favor?";
		}
		
		send.setText(resposta);
		
		try {
			execute(send);
		}
		catch(TelegramApiException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public String getBotToken() {
		return "596049362:AAEzVTpO-dKQyPqfzZKskmbLGk5SI4sLD3U";
	}

	public static void main(String[] args) {
		ApiContextInitializer.init();
		TelegramBotsApi telegramBot = new TelegramBotsApi();
		MinhaAssociacaoBot bot = new MinhaAssociacaoBot();
		
		try {
			telegramBot.registerBot(bot);
		} catch (TelegramApiRequestException e) {
			System.out.println("Erro no bot MinhaAssociacao.");
			e.printStackTrace();
		}

	}
}
