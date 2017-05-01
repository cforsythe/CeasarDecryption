import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class CeasarDecryption extends Application{

	int key = 0;
	
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
	}
}
