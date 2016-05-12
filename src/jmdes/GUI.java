package jmdes;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import twitter4j.TwitterException;

import java.util.LinkedList;

/**
 * Created by JMDeS on 5/12/2016.
 */
public class GUI extends Application{
    TwitterWebAPI twitter;
    LinkedList<String> tweetsA;
    LinkedList<String> tweetsB;
    TextFlow textFlowLeft;
    TextFlow textFlowRight;
    Stage stage;
    GridPane grid = new GridPane();
    ScrollPane leftScrollPane = new ScrollPane();
    ScrollPane rightScrollPane = new ScrollPane();

    public static void main (String[] args){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        twitter = new TwitterWebAPI();
        stage = primaryStage;
        stage.setTitle("Display Recent Tweets from Twitter");
        tweetsA = twitter.getTweets("ios");
        tweetsB = twitter.getTweets("azure");
        textFlowLeft = new TextFlow();
        textFlowLeft.setLayoutX(40);
        textFlowLeft.setLayoutY(40);
        textFlowRight = new TextFlow();
        textFlowRight.setLayoutX(40);
        textFlowRight.setLayoutY(40);
        Button btn = new Button();
        btn.setText("Refresh Tweets");
        btn.setOnAction(new EventHandler<ActionEvent>() {

            public void handle(ActionEvent event) {
                try {
                    tweetsA = twitter.getTweets("ios");
                    tweetsB = twitter.getTweets("azure");
                } catch (TwitterException e) {
                    e.printStackTrace();
                }
                refresh();


            }
        });
        Text ios = new Text("#ios");
        Text azure = new Text("#azure");
        ios.setFont(Font.font ("Verdana", 20));
        azure.setFont(Font.font ("Verdana", 20));

        leftScrollPane.autosize();
        rightScrollPane.autosize();
        grid.setAlignment(Pos.TOP_RIGHT);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.add(btn,0,0);
        grid.add(ios,0,1);
        grid.add(azure,1,1);
        refresh();
        stage.setScene(new Scene(grid, 800, 400));
        stage.show();
    }

    private void refresh(){
        leftScrollPane = new ScrollPane();
        rightScrollPane = new ScrollPane();
        textFlowLeft = new TextFlow();
        textFlowRight = new TextFlow();

        for (String tweet : tweetsA){
            textFlowLeft.getChildren().add(new Text(tweet));
            leftScrollPane.setContent(textFlowLeft);
            System.out.println(tweet);
        }
        for (String tweet : tweetsB){
            textFlowRight.getChildren().add(new Text(tweet));
            rightScrollPane.setContent(textFlowRight);
            System.out.println(tweet);
        }
        grid.add(leftScrollPane,0,2);
        grid.add(rightScrollPane,1,2);

    }
}
