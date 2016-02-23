package teachapps.gocheck;

import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.LinkedList;

/**
 * Created by Luis on 21/02/2016.
 * Se encarga de manerar las filas(row) de PautaRadioButtons que representan una pregunta
 *
 */
public class PautaRadioButtonHandler {
    Activity activity;
    int numberOfRows;
    int numberOfButtons;
    LinkedList<PautaRadioButtons> mButtons = new LinkedList();

    //contructor
    PautaRadioButtonHandler(Activity activity, int numberOfRows, int numberOfButtons){
        this.activity = activity;
        this.numberOfRows = numberOfRows;
        this.numberOfButtons = numberOfButtons;
        this.findRadioButtons(activity);
    }

    void findRadioButtons(Activity act){
        for(int row=0; row<numberOfRows; row++){
            PautaRadioButtons radioButtons = new PautaRadioButtons(activity, this, row, numberOfButtons);
            mButtons.add(radioButtons);
        }
    }

    void check(int row, int buttonNumber){
        this.mButtons.get(row).check(buttonNumber);
    }
}

/**
 * Representa a una pregunta, sus radiobuttons(alternativas) y el boton de refresh/clean.
 *
 */
class PautaRadioButtons{
    int numberOfButtons;
    int row;
    LinkedList<RadioButton> radioButtonsList = new LinkedList();
    Button cleanButton;

    //contructor
    PautaRadioButtons(Activity act, PautaRadioButtonHandler handler, int row, int numberOfButtons){
        this.numberOfButtons = numberOfButtons;
        this.row = row;
        for(int i=0; i<numberOfButtons; i++){
            //agregar los radiobuttons a la lista
            String elementName = "radioButtonRow" + (row+1) + "_" + (i+2); //ajuste debido a que los radiobuttons empiezan en la columna 2.
            int id = act.getResources().getIdentifier(elementName, "id", act.getPackageName());
            RadioButton radioButton = (RadioButton) act.findViewById(id);
            radioButtonsList.add(radioButton);
            radioButton.setOnClickListener(new onClickRadioButton(handler, row, i));
        }

        //find clean button
        String ButtonName = "buttonRefreshPreguntaRow" + (row+1);
        int id = act.getResources().getIdentifier(ButtonName, "id", act.getPackageName());
        cleanButton = (Button) act.findViewById(id);
        cleanButton.setOnClickListener(new onClickCleanButton(this));


    }

    void check(int buttonNumber){
        for(int i=0; i<this.numberOfButtons; i++){
            radioButtonsList.get(i).setChecked(false);
        }
        this.radioButtonsList.get(buttonNumber).setChecked(true);
    }

    public void clean() {
        for(int i=0; i<numberOfButtons; i++){
            this.radioButtonsList.get(i).setChecked(false);
        }
    }
}

/**
 * Desde la activity se crea un PautaRadioButtonHandler,
 * éste recibe como parametro la activity, el número de filas (rows) y la cantidad de botones(alternativas) por fila.
 * Por cada fila se crea un PautaRadioButtons que representa a la fila con sus radiobuttons y el button de refresh.
 * Estos radiobuttons se van recuperando por id y son agregados a una lista,
 * en ese momento se crea y se les asigna un nuevo onClickRadioButton que tiene datos importantes del boton
 * estos datos son:
 *      índice del boton: posición que ocupa el boton en la lista)
 *      row: fila o número de pregunta al que corresponde el boton
 *      pautaRadioButtonHandler: el handler que maneja los radiobuttons
 * El onClickRadioButton redefine el método onClick para que cuando se haga click en el radiobutton se "informe al handler"
 * , es decir, hacer que el handler ejecute el método check.
 *      (El handler despues se encarga de ejecutar el método check del PautaRadioButtons (fila) correspondiente)
 */
class onClickRadioButton implements View.OnClickListener {
    private int buttonIndex;
    private int row;
    private PautaRadioButtonHandler pautaRadioButtonHandler;
    onClickRadioButton(PautaRadioButtonHandler handler ,int row, int buttonIndex) {
        this.pautaRadioButtonHandler = handler;
        this.buttonIndex = buttonIndex;
        this.row = row;
    }

    @Override
    public void onClick(View v) {
        pautaRadioButtonHandler.check(row, buttonIndex);
    }
}

class onClickCleanButton implements View.OnClickListener {
    PautaRadioButtons row;

    onClickCleanButton(PautaRadioButtons row){
        this.row = row;
    }

    @Override
    public void onClick(View v) {
        row.clean();
    }
}