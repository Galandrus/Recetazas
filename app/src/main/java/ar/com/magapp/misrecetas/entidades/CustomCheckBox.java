package ar.com.magapp.misrecetas.entidades;

import android.content.Context;
import android.widget.CheckBox;
import android.widget.LinearLayout;

public class CustomCheckBox extends android.support.v7.widget.AppCompatCheckBox {

    private LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

    public CustomCheckBox(Context context) {
        super(context);
        setAparienciaInicial();
    }

    private void setAparienciaInicial() {
        setLayoutParams(params);
    }

    public void setBoxForIngrediente(){
        params.weight=1f;
        setLayoutParams(params);
    }

}
