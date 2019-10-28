package app.easylink.shuterstockimages.shutterRemote;

import app.easylink.shuterstockimages.shutterRemote.models.MyShutterImages;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface ShutterApi {
    /**
     *
     *   Authorization is the authentication for the shutterStock website
     *   which values is injected from ShutterRepository.class in remote package
     *   @Inject ShutterApi api;
     *
     * @param authorization
     * @param page
     * @param perPage
     * @Single public abstract class Single<T>
     * The Single class implements the Reactive Pattern for a single value response.
     * Single behaves similarly to Observable except that it can only emit either a single successful value or an error (there is no "onComplete"
     * notification as there is for an Observable).
     *
     * The Single class implements the SingleSource base interface and the default consumer type it interacts with is the SingleObserver via the
     * subscribe(SingleObserver) method.
     *
     * The Single operates with the following sequential protocol:
     *
     *      onSubscribe (onSuccess | onError)?
     * @return
     */
    @GET("images/search")
    Single<MyShutterImages> getImages(
            @Header("Authorization") String authorization, @Query("page") int page,
            @Query("per_page") int perPage);
}
