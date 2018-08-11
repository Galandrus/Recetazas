package ar.com.magapp.misrecetas.entidades;

import android.content.Context;
import android.widget.LinearLayout;

public class CustomLinearLayout extends LinearLayout {

    private LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);

    public CustomLinearLayout(Context context) {
        super(context);
        setAparienciaInicial();
    }

    private void setAparienciaInicial() {
        setLayoutParams(params);
    }


    public void setForIngredientes(){
        setOrientation(HORIZONTAL);
    }

    public void setForIngredientesForRow(){
        setOrientation(HORIZONTAL);
        params.width=LayoutParams.WRAP_CONTENT;
        params.weight=1f;
        params.setMargins(15,0,0,0);
        setLayoutParams(params);
    }

    public void setForPreparacion(){
        params.width=LayoutParams.WRAP_CONTENT;
        params.setMargins(15,0,0,0);
        setLayoutParams(params);
        setOrientation(HORIZONTAL);
    }

    public void setForTips(){
        params.width=LayoutParams.WRAP_CONTENT;
        params.setMargins(15,0,0,0);
        setLayoutParams(params);
        setOrientation(HORIZONTAL);
    }
}
