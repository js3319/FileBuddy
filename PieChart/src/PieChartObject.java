
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
public class PieChartObject extends Application  {

	@Override
	public void start(Stage primaryStage) throws Exception {
		ObservableList<PieChart.Data> piechart = FXCollections.observableArrayList(
				//Elements of the PieChart(Copy my method to add stuff to the chart
				new PieChart.Data("pdf", 2),
				new PieChart.Data("biya", 20),
				new PieChart.Data("nig", 40),
				new PieChart.Data("nig", 40),
				new PieChart.Data("nig", 40)
				
				);
		//Creates the PieChart
		PieChart pChart = new PieChart(piechart);
		Group g = new Group(pChart);
		//Creates the window for the user to see(window = Scene)
		Scene s = new Scene(g, 600, 400);
		//Title of the window(You will see it in the top left)
		primaryStage.setTitle("BIYA");
		//Displays the Scene
		primaryStage.setScene(s);
		primaryStage.show();
			
		
	}
	public static void main(String[] args) {
		launch(args);
	}
	
}
