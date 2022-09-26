package tr.com.mkk.esir.auth.service;

import lombok.AllArgsConstructor;
import lombok.Value;

import java.util.List;

@Value
@AllArgsConstructor(staticName = "of")
public class KeyList {
    List<Key> keys;
}
