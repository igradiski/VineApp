import { StyleSheet, Text, View } from "react-native";

export const HomePage: React.FC = () => {
  return (
    <View style={styles.container}>
      <Text>Home page</Text>
    </View>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: "#a0d911",
    alignItems: "center",
    justifyContent: "center",
  },
});
