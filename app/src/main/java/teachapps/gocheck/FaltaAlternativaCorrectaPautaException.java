package teachapps.gocheck;

/**
 * Created by Luis on 23/02/2016.
 */
public class FaltaAlternativaCorrectaPautaException extends java.lang.Exception {
    public static final long serialVersionUID = 42L;
    private int PreguntaSinAlternativa;

    //constructor
    FaltaAlternativaCorrectaPautaException(int preguntaSinAlternativa){
        this.PreguntaSinAlternativa = preguntaSinAlternativa +1; //Para el usuario parten desde 1, no desde 0.
    }

    @Override
    public String getMessage() {
        return "La pregunta " +  PreguntaSinAlternativa +" no tiene alternativa correcta";
    }
}
