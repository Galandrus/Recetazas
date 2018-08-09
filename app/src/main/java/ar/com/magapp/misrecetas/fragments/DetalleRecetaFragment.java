package ar.com.magapp.misrecetas.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

import ar.com.magapp.misrecetas.R;
import ar.com.magapp.misrecetas.entidades.Ingrediente;
import ar.com.magapp.misrecetas.entidades.Receta;

import static ar.com.magapp.misrecetas.R.id.idFotoRecetaDetalle;
import static ar.com.magapp.misrecetas.R.id.text;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DetalleRecetaFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DetalleRecetaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetalleRecetaFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    ImageView fotoDetalle;
    TextView nombreDetalle,descripcionDetalle;
    ArrayList<TextView> listaPreparacion, listaTips, listaIngredientes, listaCantidades;


    public DetalleRecetaFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DetalleRecetaFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DetalleRecetaFragment newInstance(String param1, String param2) {
        DetalleRecetaFragment fragment = new DetalleRecetaFragment();
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

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View vista = inflater.inflate(R.layout.fragment_detalle_receta, container, false);

        fotoDetalle= vista.findViewById(R.id.idFotoRecetaDetalle);
        nombreDetalle = vista.findViewById(R.id.idNombreRecetaDetalle);
        descripcionDetalle =  vista.findViewById(R.id.idDescRecetaDetalle);

        //Estos se crean dinamicamente.
        listaPreparacion = new ArrayList<TextView>();
        listaTips = new ArrayList<TextView>();
        listaIngredientes = new ArrayList<TextView>();
        listaCantidades = new ArrayList<TextView>();

        Bundle objetoReceta =getArguments();
        Receta receta = null;
        if (objetoReceta != null){
            receta = (Receta) objetoReceta.getSerializable("mandoReceta");

            //Seteo detalles
            fotoDetalle.setImageAlpha(receta.getFoto());
            nombreDetalle.setText(receta.getNombre());
            descripcionDetalle.setText(receta.getDescripcion());

            //Creo y seteo ingredientes
            ArrayList<Ingrediente> listaIngrediente = receta.getIngredientes();



            //Creo y seteo Preparacion
            ArrayList<String> listaPasos = receta.getPasos();



            //Creo y seteo Tips
            ArrayList<String> listaTips =  receta.getTips();




        }
        return vista;
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
