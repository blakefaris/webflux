import {check} from 'k6'
import http from 'k6/http'

export const get = function({path}) {
    const response = http.get(`http://host.docker.internal:8080/${path}`)

    // checks that the response status is 200
    check(
        response,
        {'status is 200:': (r) => r.status === 200},
        // tag, that is used for easy identification in the threshold
        {requestStatus: '200'},
    )
}