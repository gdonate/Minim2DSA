package edu.upc.dsa.minimo2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdaptadorRecycleView extends RecyclerView.Adapter<AdaptadorRecycleView.ViewHolder>
{

    private Context context;
    private List<Repos> repositorios;

    public void setData (List<Repos> repositorios )
    {
        this.repositorios = repositorios;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public AdaptadorRecycleView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        context = parent.getContext();
        return new AdaptadorRecycleView.ViewHolder(LayoutInflater.from(context).inflate(R.layout.rowusuario_layout,parent,false));
    }
    @Override
    public void onBindViewHolder(@NonNull AdaptadorRecycleView.ViewHolder holder, int position)
    {
        Repos repositorio = repositorios.get(position);
        String nombre = repositorio.getName();
        String lenguageProgramacion = repositorio.getLanguage();
        holder.lenguage.setText(lenguageProgramacion);
        holder.nombre.setText(nombre);

    }

    @Override
    public int getItemCount() {
        return repositorios.size();
    }



    public class ViewHolder extends  RecyclerView.ViewHolder
    {
        TextView nombre;
        TextView lenguage;
        public View layout;

        public ViewHolder(@NonNull View itemView)
        {

            super(itemView);
            layout = itemView;

            lenguage = itemView.findViewById(R.id.lenguage);
            nombre = itemView.findViewById(R.id.nombre);

        }
    }

}
