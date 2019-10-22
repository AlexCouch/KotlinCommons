module kotlincommons {
    requires org.apache.commons.lang3;

    requires static ubjson;
    requires static org.joml;
    requires static com.github.simplenet;

    exports bvanseg.kcommons.any;
    exports bvanseg.kcommons.assets;
    exports bvanseg.kcommons.buffers;
    exports bvanseg.kcommons.bytes;
    exports bvanseg.kcommons.classes;
    exports bvanseg.kcommons.collections;
    exports bvanseg.kcommons.compression;
    exports bvanseg.kcommons.joml.vectors;
    exports bvanseg.kcommons.kclasses;
    exports bvanseg.kcommons.logging;
    exports bvanseg.kcommons.numbers;
    exports bvanseg.kcommons.projects;
    exports bvanseg.kcommons.simplenet.packet;
    exports bvanseg.kcommons.string;
    exports bvanseg.kcommons.system;
    exports bvanseg.kcommons.time;
    exports bvanseg.kcommons.ubjson;
}