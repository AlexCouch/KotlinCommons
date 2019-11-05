/**
 * The module info for KotlinCommons.
 *
 * @author Boston Vanseghi
 */
module kotlincommons {
    requires org.apache.commons.lang3;

    requires static org.junit.jupiter;
    requires static ubjson;
    requires static org.joml;
    requires static com.github.simplenet;
    requires org.slf4j;

    requires kotlin.stdlib;
    requires java.management;

    exports bvanseg.kotlincommons.any;
    exports bvanseg.kotlincommons.assets;
    exports bvanseg.kotlincommons.booleans;
    exports bvanseg.kotlincommons.buffers;
    exports bvanseg.kotlincommons.classes;
    exports bvanseg.kotlincommons.collections;
    exports bvanseg.kotlincommons.comparable;
    exports bvanseg.kotlincommons.compression;
    exports bvanseg.kotlincommons.files;
    exports bvanseg.kotlincommons.joml.vectors;
    exports bvanseg.kotlincommons.kclasses;
    exports bvanseg.kotlincommons.logging;
    exports bvanseg.kotlincommons.measurement;
    exports bvanseg.kotlincommons.numbers;
    exports bvanseg.kotlincommons.observable;
    exports bvanseg.kotlincommons.projects;
    exports bvanseg.kotlincommons.simplenet.packet;
    exports bvanseg.kotlincommons.streams;
    exports bvanseg.kotlincommons.string;
    exports bvanseg.kotlincommons.system;
    exports bvanseg.kotlincommons.time;
    exports bvanseg.kotlincommons.tuples;
    exports bvanseg.kotlincommons.ubjson;
}