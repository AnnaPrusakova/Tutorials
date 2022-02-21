import DataService from "../services/service";
import {CREATE_TUTORIAL, RETRIEVE_TUTORIALS} from "./types";

export const createTutorial = (title: string, description: string) => async (dispatch) => {
    try {
        const result = await DataService.create({ title, description });
        dispatch({
            type: CREATE_TUTORIAL,
            playload: result.data,
        });
        return Promise.resolve(result.data);
    } catch (err) {
        return Promise.reject(err);
    }
};

export const retrieveTutorials = () => async (dispatch) => {
    try {
        const result = await DataService.getAll();
        dispatch({
            type: RETRIEVE_TUTORIALS,
            playload: result.data,
        });
    } catch (err) {
        console.log(err);
    }
};

