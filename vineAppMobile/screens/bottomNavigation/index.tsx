import { createMaterialBottomTabNavigator } from "@react-navigation/material-bottom-tabs";
import { Ionicons } from "@expo/vector-icons";
import { LoginScreen } from "../loginScreen";
import { RegisterScreen } from "../registerScreen";

const Tab = createMaterialBottomTabNavigator();

export const BottomNav: React.FC = () => {
  return (
    <Tab.Navigator
      sceneAnimationEnabled={false}
      barStyle={{ backgroundColor: "#7cb305" }}
    >
      <Tab.Screen
        name="Prijava"
        component={LoginScreen}
        options={{
          tabBarLabel: "Login",
          tabBarIcon: ({ color }) => (
            <Ionicons name="log-in-outline" color={color} size={25} />
          ),
        }}
      />
      <Tab.Screen
        name="Registracija"
        component={RegisterScreen}
        options={{
          tabBarLabel: "Register",
          tabBarIcon: ({ color }) => (
            <Ionicons name="add-circle-outline" color={color} size={25} />
          ),
        }}
      />
    </Tab.Navigator>
  );
};
