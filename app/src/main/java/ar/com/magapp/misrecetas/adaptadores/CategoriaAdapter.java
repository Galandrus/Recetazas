package ar.com.magapp.misrecetas.adaptadores;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import ar.com.magapp.misrecetas.R;
import ar.com.magapp.misrecetas.entidades.Receta;

public class CategoriaAdapter extends RecyclerView.Adapter<CategoriaAdapter.CategoriasViewHolder> implements View.OnClickListener{

    ArrayList<String> listaCategorias;
    private View.OnClickListener listener;

    public CategoriaAdapter(ArrayList<String> listaCategorias) {
        this.listaCategorias = listaCategorias;
    }

    @Override
    public CategoriasViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_categorias, null, false);
        view.setOnClickListener(this);
        return new CategoriasViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CategoriasViewHolder holder, int position) {
        holder.nombreCategoria.setText(listaCategorias.get(position));
    }

    @Override
    public int getItemCount() {
        return listaCategorias.size();
    }

    public void setOnClickListener(View.OnClickListener listener){
        this.listener = listener;
    }

    @Override
    public void onClick(View view) {
        if (listener != null){
            listener.onClick(view);
        }
    }

    public class CategoriasViewHolder extends RecyclerView.ViewHolder {

        TextView nombreCategoria;

        public CategoriasViewHolder(View itemView) {
            super(itemView);
            nombreCategoria= itemView.findViewById(R.id.idNombreCategoria);
        }
    }
}
