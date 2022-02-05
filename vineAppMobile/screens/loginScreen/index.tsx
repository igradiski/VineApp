import { Formik } from "formik";
import React from "react";
import { StyleSheet, TextInput, Text, View, Pressable } from "react-native";
import * as yup from "yup";
import { useDispatch } from "react-redux";
import { useNavigation } from "@react-navigation/core";
import { authenticate } from "../../store/slices/user";
import { store } from "../../store/store";

export const LoginScreen: React.FC = () => {
  const dispatch = useDispatch();
  const navigation = useNavigation();
  const initialValues = {
    name: "",
    surname: "",
    username: "",
    password: "",
    email: "",
  };
  const validationSchema = yup.object().shape({
    password: yup
      .string()
      .min(8, ({ min }) => `Password must be at least ${min} characters`)
      .required("Password is required"),
    username: yup.string().required("Username Address is Required!"),
  });
  const handleLogin = async (values: IUserData) => {
    try {
      await dispatch(authenticate(values));
    } catch (error: any) {
      console.log(error);
    }
  };

  return (
    <Formik
      validationSchema={validationSchema}
      initialValues={initialValues}
      validateOnMount={true}
      onSubmit={async (values, form) => {
        await handleLogin(values);
        form.setSubmitting(false);
      }}
    >
      {({
        handleBlur,
        handleChange,
        handleSubmit,
        isSubmitting,
        errors,
        isValid,
        touched,
      }) => (
        <View style={styles.container}>
          <Text style={styles.textLabel}>Username:</Text>
          <TextInput
            style={styles.input}
            onChangeText={handleChange("username")}
            onBlur={handleBlur("username")}
          />
          {errors.username && touched.username && (
            <Text style={styles.errorText}>{errors.username}</Text>
          )}
          <Text style={styles.textLabel}>Password:</Text>
          <TextInput
            style={styles.input}
            onChangeText={handleChange("password")}
            onBlur={handleBlur("password")}
            secureTextEntry={true}
          />
          {errors.password && touched.password && (
            <Text style={styles.errorText}>{errors.password}</Text>
          )}
          <Pressable
            style={styles.button}
            onPress={() => handleSubmit()}
            disabled={!isValid || isSubmitting}
          >
            <Text style={styles.text}>Prijava</Text>
          </Pressable>
        </View>
      )}
    </Formik>
  );
};

const styles = StyleSheet.create({
  errorText: {
    fontSize: 10,
    color: "red",
    fontWeight: "bold",
  },
  textLabel: {
    fontSize: 16,
    color: "black",
    fontWeight: "bold",
  },
  container: {
    flex: 1,
    backgroundColor: "#a0d911",
    alignItems: "center",
    justifyContent: "center",
  },
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
    borderWidth: 1,
    padding: 10,
    backgroundColor: "white",
    borderRadius: 10,
  },
});
