import { useParams,useLocation } from "react-router-dom";

import styles from './Detail.module.css';

console.groupCollapsed('src/component/Detail.js'); console.groupEnd();

const Detail = (props) =>{
    console.groupCollapsed('Detail(',props,') invoked.'); console.groupEnd();


    // 동적 세그먼트 얻음 useParams로
    // const params = useParams();
    // console.log('params:', params);

    // <Link state/> state 정보를 받으려면 useLocation
    const location = useLocation();
    console.log('location:', location);

    const {state:movie} = location;
    console.log('movie:', movie);

    console.groupEnd();

    return (
        <div className={styles.movie}>

             <img src={movie.large_cover_image} 
                alt={movie.title} title={movie.title} className={styles.movie__img}/>


            <div>
                <h2 className={styles.movie__title}>{movie.title}</h2>
                <h3 className={styles.movie__year}>{movie.year}</h3>

                <p className={styles.movie__summary}>{movie.summary}</p>

                <ul className={styles.movie__genres}>
                    {movie.genres.map((g,i) => <li key={i}> {g} </li>)}
                </ul>

            </div>

        </div>
    );
};

export default Detail;