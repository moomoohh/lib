import java.awt.CardLayout;
import javax.swing.JPanel;
public class CardPanel extends JPanel{
	private QueryPanel queryPanel;
	private EnterBooks enterPanel;
	public CardPanel()
	{
		CardLayout card=new CardLayout();
		setLayout(card);
		enterPanel=new EnterBooks(2,this,card);
		queryPanel=new QueryPanel(2, this,card,enterPanel);//,userId
		add("query",queryPanel);
		add("update",enterPanel);
		card.show(this, "query");
	}

}
