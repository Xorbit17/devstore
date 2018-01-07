package be.qbmt.dv.devstore;

import android.app.Fragment;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import static be.qbmt.dv.devstore.Util.priceConvert;

/**
 * Created by dv on 28/12/2017.
 */

public class DetailFragment extends Fragment {
    private static final String TAG = DetailFragment.class.getSimpleName();
    private ImageView imageView;
    private TextView productNameView;
    private TextView descriptionView;
    private TextView priceView;
    private ImageButton buyButton;

    public DetailFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.detailview,container,false);

        imageView = (ImageView)view.findViewById(R.id.detailimageview);
        productNameView = (TextView)view.findViewById(R.id.detailproductname);
        descriptionView = (TextView)view.findViewById(R.id.detaildescription);
        priceView = (TextView)view.findViewById(R.id.detailprice);
        buyButton = (ImageButton)view.findViewById(R.id.detailbuybutton);

        processArguments();

        return view;
    }

    public void processArguments() {
        Bundle args = getArguments();
        productNameView.setText(args.getString(Arguments.NAME));
        descriptionView.setText(args.getString(Arguments.DESCRIPTION));
        priceView.setText(priceConvert(args.getInt(Arguments.PRICE)));

        String fileUrl = "file:///android_asset/" + args.getString(Arguments.IMAGE);
        Log.d(TAG, "setProduct: fileUrl:" + fileUrl);

        Uri imageUri = Uri.parse(fileUrl);

        Glide.with(getActivity()).asBitmap().load(imageUri).into(imageView);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    public static class Arguments {
        public static final String IMAGE = "image";
        public static final String NAME = "name";
        public static final String DESCRIPTION = "description";
        public static final String PRICE = "price";
    }
}
