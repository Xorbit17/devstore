package be.qbmt.dv.devstore;

import com.bumptech.glide.load.model.UriLoader;

/**
 * Created by dv on 29/12/2017.
 */

public class CustomUriLoader extends UriLoader {
    public CustomUriLoader(LocalUriFetcherFactory factory) {
        super(factory);
    }

}
