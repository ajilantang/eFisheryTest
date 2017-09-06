package ajilantang.wificonnect.Adapter;

/**
 * Created by ajilantang on 05/09/17.
 */

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import ajilantang.wificonnect.Model.Connection;
import ajilantang.wificonnect.R;
import ajilantang.wificonnect.View.SignalView;

public class WifiAdapter extends BaseAdapter{
    Context context;
    private List<Connection> connections;
    private static LayoutInflater inflater=null;

    public WifiAdapter(Context context, List<Connection> connections) {
        // TODO Auto-generated constructor stub
        this.connections=connections;
        this.context=context;
        inflater = ( LayoutInflater )context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    //return adaptor size based on model size

    @Override
    public int getCount() {
        return connections.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return connections.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    public class Holder
    {
        TextView tvLabel;
        SignalView signalView;
        TextView connected;
    }

    /**
     * list item for wifilist
     * @param position
     * @param convertView
     * @param parent
     * @return
     */
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        Holder holder=new Holder();
        View rowView;
        rowView = inflater.inflate(R.layout.list_item, null);
        Connection connection = (Connection) getItem(position);

        holder.signalView = (SignalView) rowView.findViewById(R.id.signal_view);
        holder.signalView.setLevel(connection.getStrength());

        holder.tvLabel=(TextView) rowView.findViewById(R.id.label);
        holder.tvLabel.setText(connection.getName());

        holder.connected = (TextView) rowView.findViewById(R.id.tv_connected);
        if(connection.getIsConnected()) {
            holder.connected.setText("connected");
        }else {
            holder.connected.setText("connect");
        }

        return rowView;
    }
}