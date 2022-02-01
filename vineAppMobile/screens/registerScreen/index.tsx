import React from "react";
import { StyleSheet, TextInput, Text, View, Pressable } from "react-native";

export const RegisterScreen: React.FC = () => {
  const [text, onChangeText] = React.useState("");

  const onPress = () => {};

  return (
    <View style={styles.container}>
      <Text>Name:</Text>
      <TextInput
        style={styles.input}
        onChangeText={onChangeText}
        value={text}
      />
      <Text>Surname:</Text>
      <TextInput
        style={styles.input}
        onChangeText={onChangeText}
        value={text}
      />
      <Text>Username:</Text>
      <TextInput
        style={styles.input}
        onChangeText={onChangeText}
        value={text}
      />
      <Text>Password:</Text>
      <TextInput
        style={styles.input}
        onChangeText={onChangeText}
        value={text}
      />
      <Text>Email:</Text>
      <TextInput
        style={styles.input}
        onChangeText={onChangeText}
        value={text}
      />
      <Pressable style={styles.button} onPress={onPress}>
        <Text style={styles.text}>Registriraj se</Text>
      </Pressable>
    </View>
  );
};

const styles = StyleSheet.create({
  button: {
    alignItems: "center",
    justifyContent: "center",
    paddingVertical: 12,
    paddingHorizontal: 32,
    borderRadius: 4,
    elevation: 3,
    backgroundColor: "black",
  },
  text: {
    fontSize: 16,
    lineHeight: 21,
    fontWeight: "bold",
    letterSpacing: 0.25,
    color: "white",
  },
  input: {
    width: "50%",
    height: 40,
    margin: 12,
    padding: 10,
    backgroundColor: "white",
    borderRadius: 10,
    borderWidth: 1,
  },
  container: {
    flex: 1,
    backgroundColor: "#a0d911",
    alignItems: "center",
    justifyContent: "center",
  },
});
