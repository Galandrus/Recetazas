package ar.com.magapp.misrecetas.entidades;


import android.content.Context;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.nio.file.attribute.AttributeView;

public class CustomTextView extends android.support.v7.widget.AppCompatTextView {

    private LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

    public CustomTextView(Context context) {
        super(context);
        setAparienciaInicial();
    }

    private void setAparienciaInicial() {
        setLayoutParams(params);
    }

    public void setIndiceForTips(){
        setTextSize(17f);
        params.setMargins(10,0,10,0);
        setLayoutParams(params);
    }

    public void setTipForTips(){
        setTextSize(14f);
        params.setMargins(0,0,10,0);
        setLayoutParams(params);
    }

    public void setIndiceForPreparacion(){
        setTextSize(17f);
        params.setMargins(10,0,10,0);
        setLayoutParams(params);
    }

    public void setPasoForPreparacion(){
        setTextSize(14f);
        params.setMargins(0,0,10,0);
        setLayoutParams(params);
    }

    public void setCantForIngredientes(){
        params.weight= 25f;
        params.setMargins(5,0,0,0);
        setLayoutParams(params);
    }

    public void setIngForIngredientes(){
        params.weight= 1.0f;
        setLayoutParams(params);
    }

}
