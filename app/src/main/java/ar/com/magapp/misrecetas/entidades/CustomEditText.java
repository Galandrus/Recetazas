package ar.com.magapp.misrecetas.entidades;

import android.content.Context;
import android.graphics.Color;
import android.text.InputType;
import android.widget.EditText;
import android.widget.LinearLayout;

public class CustomEditText extends android.support.v7.widget.AppCompatEditText {

    private LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

    public CustomEditText(Context context) {
        super(context);
        setAparienciaInicial();
    }

    private void setAparienciaInicial() {
        setLayoutParams(params);
        setInputType(InputType.TYPE_TEXT_FLAG_CAP_SENTENCES);
        //setHintTextColor(Color.GRAY);
    }

    public void setDetalles(){
        params.setMargins(15,15,15,0);
        setLayoutParams(params);
    }

    public void setIngredientes(){
        params.setMargins(45,15,0,0);
        params.weight=1f;
        setLayoutParams(params);
    }

    public void setCantidad(){
        params.setMargins(45,15,45,0);
        params.weight=1f;
        setLayoutParams(params);
    }

    public void setPreparacion(){
        params.setMargins(45,15,45,0);
        setLayoutParams(params);
    }

    public void setTips(){
        params.setMargins(45,15,45,0);
        setLayoutParams(params);
    }

}
