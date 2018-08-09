package ar.com.magapp.misrecetas.adaptadores;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

import ar.com.magapp.misrecetas.R;
import ar.com.magapp.misrecetas.entidades.Receta;

public class RecetaAdapter extends RecyclerView.Adapter<RecetaAdapter.RecetasViewHolder> implements View.OnClickListener{

    ArrayList<Receta> listaRecetas;
    private View.OnClickListener listener;

    public RecetaAdapter(ArrayList<Receta> listaRecetas) {
        this.listaRecetas = listaRecetas;
    }


    @Override
    public RecetasViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_recetas, null, false);
        view.setOnClickListener(this);
        return new RecetasViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBindViewHolder(RecetasViewHolder holder, int position) {
        holder.recetaNombre.setText(listaRecetas.get(position).getNombre());
        holder.recetaDesc.setText(listaRecetas.get(position).getDescripcion());
        holder.recetaFoto.setImageAlpha(listaRecetas.get(position).getFoto());
    }

    @Override
    public int getItemCount() {
        return listaRecetas.size();
    }

    @Override
    public void onClick(View view) {
        if (listener != null){
            listener.onClick(view);
        }
    }

    public void setOnClickListener(View.OnClickListener listener){
        this.listener = listener;
    }

    public class RecetasViewHolder extends RecyclerView.ViewHolder {

        TextView recetaNombre, recetaDesc;
        ImageView recetaFoto;

        public RecetasViewHolder(View itemView) {
            super(itemView);
            recetaNombre=(TextView)itemView.findViewById(R.id.idNombreReceta);
            recetaDesc=(TextView) itemView.findViewById(R.id.idDescReceta);
            recetaFoto=(ImageView) itemView.findViewById(R.id.idFotoReceta);
        }
    }
}
