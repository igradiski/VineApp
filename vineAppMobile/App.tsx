import { BottomNav } from "./screens/bottomNavigation";
import { NavigationContainer } from "@react-navigation/native";
import { Provider } from "react-redux";
import { store } from "./store/store";
import { injectStore } from "./api/axios";

injectStore(store);
export default function App() {
  return (
    <Provider store={store}>
      <NavigationContainer>
        <BottomNav />
      </NavigationContainer>
    </Provider>
  );
}
