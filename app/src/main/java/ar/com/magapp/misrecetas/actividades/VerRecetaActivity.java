package ar.com.magapp.misrecetas.actividades;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import ar.com.magapp.misrecetas.R;
import ar.com.magapp.misrecetas.entidades.Receta;
import ar.com.magapp.misrecetas.fragments.DetalleRecetaFragment;
import ar.com.magapp.misrecetas.fragments.ListaCategoriasFragment;
import ar.com.magapp.misrecetas.fragments.ListaRecetasFragment;
import ar.com.magapp.misrecetas.interfaces.IComunicacionFragment;

public class VerRecetaActivity extends AppCompatActivity implements IComunicacionFragment,ListaRecetasFragment.OnFragmentInteractionListener, DetalleRecetaFragment.OnFragmentInteractionListener, ListaCategoriasFragment.OnFragmentInteractionListener {

    ListaRecetasFragment recetasFragment;
    DetalleRecetaFragment detalleRecetaFragment;
    ListaCategoriasFragment categoriasFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_receta);


        categoriasFragment=new ListaCategoriasFragment();

        getSupportFragmentManager().beginTransaction().replace(R.id.contenedorFragment,categoriasFragment).commit();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void enviarCategoria(String categoria) {
        recetasFragment=new ListaRecetasFragment();
        Bundle bundleEnvio =new Bundle();
        bundleEnvio.putSerializable("mandoCategoria",categoria);
        recetasFragment.setArguments(bundleEnvio);

        getSupportFragmentManager().beginTransaction().replace(R.id.contenedorFragment, recetasFragment).addToBackStack(null).commit();
    }

    @Override
    public void enviarReceta(Receta receta) {
        detalleRecetaFragment=new DetalleRecetaFragment();
        Bundle bundleEnvio =new Bundle();
        bundleEnvio.putSerializable("mandoReceta",receta);
        detalleRecetaFragment.setArguments(bundleEnvio);

        getSupportFragmentManager().beginTransaction().replace(R.id.contenedorFragment, detalleRecetaFragment).addToBackStack(null).commit();
    }
}
