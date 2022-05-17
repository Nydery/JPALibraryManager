package at.htlleonding.misc;

import java.lang.annotation.*;

/*
 Annotation to mark fields as part of the business key of an entity.
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface BusinessKey {
}
