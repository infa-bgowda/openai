import java.util.Scanner;

import com.theokanning.openai.completion.CompletionRequest;
import com.theokanning.openai.service.OpenAiService;

public class PrintMe {

	OpenAiService service = new OpenAiService("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");

	public static void main(String[] args) {
		System.out.println("Enter the query:");
		PrintMe pm = new PrintMe();
		pm.createConnector();
		System.out.println("Bye For Now");
	}

	private void createConnector() {

		Scanner scanner = new Scanner(System.in);

		while (true) {
			String inputString = scanner.nextLine();
			if(inputString.contains("jdbc jar download url")) {
				getJDBCJarDownloadURL(inputString);
				continue;
			}
			CompletionRequest completionRequest = CompletionRequest.builder().prompt(inputString).maxTokens(100)
					.model("text-davinci-003").echo(false).temperature(0.0).build();
			System.out.println(service.createCompletion(completionRequest).getChoices().get(0).getText());
		}
	}

	private void getJDBCJarDownloadURL(String query) {
		CompletionRequest completionRequest = CompletionRequest.builder().prompt(query).maxTokens(100)
				.model("text-davinci-003").echo(false).temperature(0.0).build();
		String text = service.createCompletion(completionRequest).getChoices().get(0).getText();
		System.out.println("***********"+text.substring(text.indexOf("https")));
	}

}
