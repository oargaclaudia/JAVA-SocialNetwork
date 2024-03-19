package com.utils.observer;

import com.utils.events.Event;
// An observable is an object which notifies observers about the changes in its state.
//
//For example, a news agency can notify channels when it receives news.
// Receiving news is what changes the state of the news agency,
// and it causes the channels to be notified.
public interface Observable<E extends Event>{
    void addObserver(Observer<E> e);

    void removeObserver(Observer<E> e);

    void notifyObservers(E t);
}
