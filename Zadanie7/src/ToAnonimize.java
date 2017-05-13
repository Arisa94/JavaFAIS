import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target( ElementType.FIELD ) // ta adnotacje mozna zastosowac tylko do pol
public @interface ToAnonimize {
	boolean readyToAnonimize();
}