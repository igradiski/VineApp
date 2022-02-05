import React from "react";
import {
  StyleSheet,
  TextInput,
  Text,
  View,
  Pressable,
  Alert,
} from "react-native";
import { Formik } from "formik";
import { useDispatch } from "react-redux";
import { signUp } from "../../store/slices/user";
import { useNavigation } from "@react-navigation/core";
import * as yup from "yup";

export const RegisterScreen: React.FC = () => {
  const dispatch = useDispatch();
  const navigation = useNavigation();

  const initialValues: IUserData = {
    name: "",
    surname: "",
    username: "",
    password: "",
    email: "",
  };

  const handleRegistration = async (data: IUserData) => {
    try {
      await dispatch(signUp(data));
      navigation.navigate("Login" as never);
    } catch (error: any) {
      console.log(error);
    }
  };

  const validationSchema = yup.object().shape({
    email: yup
      .string()
      .email("Please enter valid email!")
      .required("Email Address is Required !"),
    password: yup
      .string()
      .min(8, ({ min }) => `Password must be at least ${min} characters`)
      .required("Password is required"),
    name: yup.string().required("Name is Required!"),
    surname: yup.string().required("Surname Address is Required!"),
    username: yup.string().required("Username Address is Required!"),
  });

  return (
    <Formik
      validationSchema={validationSchema}
      initialValues={initialValues}
      validateOnMount={true}
      onSubmit={async (values, form) => {
        await handleRegistration(values);
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
          <Text style={styles.textLabel}>Name:</Text>
          <TextInput
            style={styles.input}
            onBlur={handleBlur("name")}
            onChangeText={handleChange("name")}
          />
          {errors.name && touched.name && (
            <Text style={styles.errorText}>{errors.name}</Text>
          )}
          <Text style={styles.textLabel}>Surname:</Text>
          <TextInput
            style={styles.input}
            onChangeText={handleChange("surname")}
            onBlur={handleBlur("surname")}
          />
          {errors.surname && touched.surname && (
            <Text style={styles.errorText}>{errors.surname}</Text>
          )}
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
          <Text style={styles.textLabel}>Email:</Text>
          <TextInput
            style={styles.input}
            onChangeText={handleChange("email")}
            onBlur={handleBlur("email")}
          />
          {errors.email && touched.email && (
            <Text style={styles.errorText}>{errors.email}</Text>
          )}
          <Pressable
            style={styles.button}
            onPress={() => handleSubmit()}
            disabled={!isValid || isSubmitting}
          >
            <Text style={styles.text}>Registriraj se</Text>
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
  button: {
    marginTop: 10,
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
