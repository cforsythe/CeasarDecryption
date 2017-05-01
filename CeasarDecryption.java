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
	public String decryptText(String encrypted)
	{
	System.out.println("Nothing");
	return "";
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
		
		Scene myScene = new Scene(rootNode, 400, 300);
		rootNode.getChildren().addAll(inputBox, decrypt, decTitle, decKey, decText);
		myStage.setScene(myScene);
		myStage.show();
		
		fillMap(commonWords);
		decrypt.setOnAction(e -> decText.setText(decryptText(decrypt.getText())));
		
	}
}
