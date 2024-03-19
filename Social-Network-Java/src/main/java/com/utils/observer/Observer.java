package com.utils.observer;

import com.utils.events.Event;
//interfata pt observer
//in momentul in care fac o schimbare dau notify deci toate functiile care au fost adaugate in observer trebuie
//sa isi dea update

public interface Observer<E extends Event>{
    void update(E e);
}
