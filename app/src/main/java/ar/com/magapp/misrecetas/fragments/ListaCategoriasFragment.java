package ar.com.magapp.misrecetas.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;

import ar.com.magapp.misrecetas.R;
import ar.com.magapp.misrecetas.actividades.AgregarRecetaActivity;
import ar.com.magapp.misrecetas.adaptadores.CategoriaAdapter;
import ar.com.magapp.misrecetas.adaptadores.RecetaAdapter;
import ar.com.magapp.misrecetas.entidades.Receta;
import ar.com.magapp.misrecetas.interfaces.IComunicacionFragment;
import ar.com.magapp.misrecetas.sqlite.ConexionSQLiteHelper;
import ar.com.magapp.misrecetas.utilidades.Utilidades;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ListaCategoriasFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ListaCategoriasFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListaCategoriasFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    ArrayList<String> listaCategoria;
    RecyclerView recyclerCategoria;
    IComunicacionFragment interfazcComunicacionFragment;
    ImageButton btnAgregar;

    private OnFragmentInteractionListener mListener;
    private Activity activity;

    public ListaCategoriasFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ListaCategoriasFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ListaCategoriasFragment newInstance(String param1, String param2) {
        ListaCategoriasFragment fragment = new ListaCategoriasFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View vista = inflater.inflate(R.layout.fragment_lista_categorias, container, false);

        listaCategoria = new ArrayList<>();
        recyclerCategoria = (RecyclerView) vista.findViewById(R.id.idRecyclerCategorias);
        recyclerCategoria.setLayoutManager(new LinearLayoutManager(getContext()));
        llenarListaCategorias();
        CategoriaAdapter adapter = new CategoriaAdapter(listaCategoria);
        recyclerCategoria.setAdapter(adapter);

        adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombreCategoria =listaCategoria.get(recyclerCategoria.getChildAdapterPosition(view));
                Toast.makeText(getContext(), "Seleccion: " +nombreCategoria , Toast.LENGTH_SHORT).show();
                interfazcComunicacionFragment.enviarCategoria(nombreCategoria, getCategoriaId(nombreCategoria));
            }
        });

        btnAgregar = vista.findViewById(R.id.idBtnAgregarReceta);
        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), AgregarRecetaActivity.class);
                startActivityForResult(intent,0);
            }
        });




        return vista;
    }

    private String getCategoriaId(String nombreCategoria) {
        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(getContext());
        SQLiteDatabase db = conn.getReadableDatabase();
        Cursor cursor=db.rawQuery(Utilidades.seleccionarCategoria(nombreCategoria),null);
        String resultado;
        if (cursor.moveToFirst()){
            resultado = cursor.getString(0);
        } else
            resultado = null;

        db.close();
        return resultado;
    }

    private void llenarListaCategorias() {
        //LLENAR CATEGORIAS CON LA BD

        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(getContext());
        SQLiteDatabase db = conn.getReadableDatabase();
        Cursor cursor=db.rawQuery(Utilidades.RECUPERAR_CATEGORIAS,null);

        if (cursor.moveToFirst()) {
            listaCategoria.add(cursor.getString(0));
            while (cursor.moveToNext()) {
                listaCategoria.add(cursor.getString(0));
            }
        } else
            listaCategoria.add("No hay categoria disponible");
        db.close();

    }






    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof Activity){
            this.activity = (Activity) context;
            interfazcComunicacionFragment = (IComunicacionFragment) this.activity;
        }
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }


}
