import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import App from './App';
import storage from "redux-persist/lib/storage";
import rootReducer from "./store/rootReducer";
import { createStore, applyMiddleware } from 'redux';
import thunk from 'redux-thunk'
import { persistStore, persistReducer } from "redux-persist";
import locale from "antd/es/locale/hr_HR";
import { Provider } from 'react-redux';
import { PersistGate } from "redux-persist/integration/react";
import { ConfigProvider } from "antd";
import { composeWithDevTools } from 'redux-devtools-extension';


const persistConfig = {
  key: 'root',
  storage,
}

const persistedReducer = persistReducer(persistConfig, rootReducer);
export const store = createStore(persistedReducer, composeWithDevTools(applyMiddleware(thunk)));
let persistor = persistStore(store);
//window.store = store;

ReactDOM.render(
     <Provider store={store} >
        <PersistGate loading={null} persistor={persistor}>
            <ConfigProvider locale={locale}><App /></ConfigProvider>
        </PersistGate>
    </Provider>,
  document.getElementById('root')
);
