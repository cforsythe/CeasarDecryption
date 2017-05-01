import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class CeasarDecryption extends Application{
	
	Map<String, Integer> commonWords = new HashMap<String, Integer>();
	int key = 0;
	public void fillMap(Map<String, Integer> commonWords)
	{
	URL path = CeasarDecryption.class.getResource("mostCommonWords.txt");
	File f = new File(path.getFile());
	try (BufferedReader br = new BufferedReader(new FileReader(f))) {
		String line;
		while((line = br.readLine()) != null)
		{
			commonWords.put(line, 0);
		}
	}
	catch(FileNotFoundException e)
	{
		System.out.println("File not found");
	}
	catch(IOException e)
	{
		System.out.println("Error reading file");
	}
	}
	public int wordCount(String test)
	{
		int count = 0;
		String[] wordArray = test.split("\\s+");
		for(int i = 0; i < wordArray.length; i++)
		{
			if(commonWords.containsKey(wordArray[i].toLowerCase()))
				count++;
		}
		return count;
	}
	public String decryptText(String encrypted)
	{
	String temp = "";
	int biggestCount = 0;
	for(int i = 1; i < 26; i++)
	{
		for(int ii = 0; ii < encrypted.length();ii++)
		{
		char letter = encrypted.charAt(ii);
		if(letter >= 'A' && letter <= 'Z')
			{
			letter = (char)((letter + i) % 91);
			if(letter < 65)
				letter += 65;
			temp += (char)letter;
			}
		else if(letter >= 'a' && letter <= 'z')
			{
			letter = (char)((letter + i) % 123);
			if(letter < 97)
				letter += 97;
			temp += (char)letter;
			}
		else if(letter == ' ')
			{
			temp += " ";
			}
		}
		int count = wordCount(temp);
		if(count > biggestCount)
			{
			biggestCount = count;
			key = i;
			}
		temp = "";
		
	}
	if(biggestCount < wordCount(encrypted))
		return encrypted;
	return decryptWithKey(encrypted);
	}
	public String decryptWithKey(String encrypted)
	{
	String decrypted = "";
	for(int i = 0; i < encrypted.length(); i++)
		{
		char letter = encrypted.charAt(i);
		if(letter >= 65 && letter <= 90)
		{
		letter = (char)((letter + this.key) % 91);
		if(letter < 65)
			letter += 65;
		decrypted += (char)letter;
		}
	else if(letter >= 97 && letter <= 122)
		{
		letter = (char)((letter + this.key) % 123);
		if(letter < 97)
			letter += 97;
		decrypted += (char)letter;
		}
	else
		{
		decrypted += letter;
		}
		}
	return decrypted;
	}
	public static void main(String[] args)
	{
	launch(args);
	}

	public void start(Stage myStage){

		
		myStage.setTitle("Ceasar Cipher Decryption");
		FlowPane rootNode = new FlowPane(1, 3);
		rootNode.setAlignment(Pos.TOP_CENTER);
		
		TextField inputBox = new TextField("Enter an encrypted string here");
		inputBox.setPrefWidth(350);
		inputBox.setAlignment(Pos.TOP_LEFT);
		
		Button decrypt = new Button("Decrypt");
		decrypt.setPrefWidth(250);
		
		Label decTitle = new Label("Decrypted Text");
		decTitle.setPrefSize(150, 50);
		decTitle.setAlignment(Pos.BASELINE_LEFT);
		
		Label decKey = new Label("Key = " + key);
		decKey.setPrefSize(150, 50);
		decKey.setAlignment(Pos.BASELINE_RIGHT);
		
		Label decText = new Label("Decrypted text will show here");
		decText.setPrefSize(350, 200);
		decText.setAlignment(Pos.TOP_CENTER);
		decText.setWrapText(true);
		
		Scene myScene = new Scene(rootNode, 400, 300);
		rootNode.getChildren().addAll(inputBox, decrypt, decTitle, decKey, decText);
		myStage.setScene(myScene);
		myStage.show();
		
		fillMap(commonWords);
		decrypt.setOnAction(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent ae) {
				decText.setText(decryptText(inputBox.getText()));
				decKey.setText("Key = " + Integer.toString(key));
			}
			
			
			
			
		});
		
	}
}
