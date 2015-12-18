package edu.tryouts.rapidoid;

import java.util.List;
import java.util.Map;

import org.rapidoid.config.Conf;
import org.rapidoid.fluent.Do;
import org.rapidoid.fluent.Find;
import org.rapidoid.fluent.Flow;
import org.rapidoid.gui.Btn;
import org.rapidoid.gui.GUI;
import org.rapidoid.gui.KVGrid;
import org.rapidoid.gui.menu.PageMenu;
import org.rapidoid.http.fast.On;
import org.rapidoid.log.Log;
import org.rapidoid.u.U;


public class Main extends GUI {

    public static void main(String[] args) {

        Conf.args(args);

//The application will manage this configuration:

        Map<String, String> cfg = U.map("title", "App", "user", "u1");

//RESTful service for configuration retrieval:

        On.get("/cfg").json(cfg);

        PageMenu menu = PageMenu.from(U.map("Home", "/", "Group", "/group"));

//Wrap every result into a page:

        On.defaultWrap((data, next) -> next.invoke(x -> page(x).menu(menu).title(cfg.get("title"))));

//Create buttons to filter by starting letter:

        List<Btn> letters = Flow.chars('a', 'z').map(c -> btn(c).linkTo("/find?p=" + c)).toList();

//Display the configuration entries at the home page:

        On.page("/").gui(req -> {
            KVGrid grid = grid(cfg).keyView(k -> a(k).href("/edit?k=" + k));
            return U.list(letters, grid);
        });

//Group the configuration entries by the starting letter:

        On.page("/group").gui(req -> {
            return Do.map(Do.group(cfg).by((k, v) -> k.charAt(0)))
                    .toList((k, v) -> U.list(h3(k), grid(v)));
        });

//Edit configuration entries:

        On.page("/edit").gui("k", k -> {
            Btn ok = btn("Update").onClick(() -> Log.info("Updated!"));
            return edit(cfg, k).buttons(ok, btn("Back").linkTo("/"));
        });

//Search configuration entries by prefix:

        On.page("/find").gui("p", p -> grid(Find.allOf(cfg).where((k, v) -> k.startsWith(p))));
    }
}
